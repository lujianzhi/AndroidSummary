package com.example.lawson.androidsummery.bitmap.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.lawson.androidsummery.R;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;

/**
 * Created by Ian.Lu on 2017/9/8.
 * Project : AndroidSummary
 */

public class ImageLoader {

    //Handler的what值
    public static final int MESSAGE_POST_RESULT = 1;
    //ImageView中Tag值的key
    private static final int TAG_KEY_URI = R.id.imageloader_uri;
    //文件缓存，设定50MB
    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;
    private static final int DISK_CACHE_INDEX = 0;
    private static final int IO_BUFFER_SIZE = 8 * 1024;
    //磁盘缓存是否创建
    private boolean mIsDiskLruCacheCreated = false;

    //CPU数量
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //核心线程数量
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    //最大线程数量
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    //非核心线程闲置时间
    private static final long KEEP_ALIVE = 10L;
    //线程工厂，为每个线程创建名字
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "ImageLoader - " + mCount.getAndIncrement());
        }
    };
    /**
     * 自定义线程池
     */
    private static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(),
            sThreadFactory);

    //MainLooper的Handler
    private Handler mMainHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            LoaderResult loaderResult = (LoaderResult) msg.obj;
            ImageView imageView = loaderResult.imageView;
            //设置bitmap
            imageView.setImageBitmap(loaderResult.bitmap);
            String url = (String) imageView.getTag(TAG_KEY_URI);
            if (url.equals(loaderResult.url)) {
                //为了防止ListView中的错乱，当url值相同时，再设置一次
                imageView.setImageBitmap(loaderResult.bitmap);
            } else {
                Log.w("Ian", "给新的ImageView设置Bitmap");
            }
        }
    };

    private Context mContext;
    private ImageResizer mImageResizer = new ImageResizer();
    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;

    private ImageLoader(Context context) {
        mContext = context.getApplicationContext();
        //初始化内存缓存
        int maxMemorySize = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheMemorySize = maxMemorySize / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheMemorySize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //缓存内每一个Bitmap的大小
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };

        //初始化磁盘缓存
        File diskCacheDir = getDiskCacheDir(mContext, "bitmap");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }
        if (getUsableSpace(diskCacheDir) >= DISK_CACHE_SIZE) {
            try {
                mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, DISK_CACHE_SIZE);
                mIsDiskLruCacheCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ImageLoader build(Context context) {
        return new ImageLoader(context);
    }

    /**
     * 同步加载
     */
    public Bitmap loadBitmap(String url, int reqWidth, int reqHeight) {
        //先从内存缓存里找
        Bitmap bitmap = loadBitmapFromMemCache(url);
        if (bitmap != null) {
            Log.i("Ian", "loadBitmapFromMemCache url:" + url);
            return bitmap;
        }

        try {
            //再从磁盘缓存中找
            bitmap = loadBitmapFromDiskCache(url, reqWidth, reqHeight);
            if (bitmap != null) {
                Log.i("Ian", "loadBitmapFromDiskCache url:" + url);
                return bitmap;
            }
            bitmap = loadBitmapFromHttp(url, reqWidth, reqHeight);
            Log.i("Ian", "loadBitmapFromHttp url:" + url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //bitmap获取不到，并且本地磁盘缓存没有开启，那就重新网络请求
        if (bitmap == null && !mIsDiskLruCacheCreated) {
            Log.w("Ian", "本地磁盘缓存未生效");
            bitmap = downloadBitmapFromUrl(url);
        }

        return bitmap;
    }

    /**
     * 异步加载
     */
    public void bindBitmap(String url, ImageView imageView) {
        bindBitmap(url, imageView, 0, 0);
    }

    /**
     * 异步加载
     */
    public void bindBitmap(final String url, final ImageView imageView, final int reqWidth, final int reqHeight) {
        //为这个ImageView绑定一个uri，方便后面ImageView只对应一个uri的处理
        imageView.setTag(TAG_KEY_URI, url);
        //先从缓存中获取
        Bitmap bitmap = loadBitmapFromMemCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        Runnable loadBitmapTask = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmap(url, reqWidth, reqHeight);
                if (bitmap != null) {
                    LoaderResult loaderResult = new LoaderResult(imageView, url, bitmap);
                    mMainHandler.obtainMessage(MESSAGE_POST_RESULT, loaderResult).sendToTarget();
                }
            }
        };
        //使用线程池执行这个任务
        THREAD_POOL_EXECUTOR.execute(loadBitmapTask);
    }

    /**
     * 从Http中获取流得到Bitmap
     */
    private Bitmap downloadBitmapFromUrl(String urlString) {
        Bitmap bitmap = null;
        URLConnection urlConnection = null;
        BufferedInputStream in = null;

        try {
            URL url = new URL(urlString);
            urlConnection = url.openConnection();
            in = (BufferedInputStream) urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            Log.e("Ian", "downloadBitmapFromUrl : " + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * 从Http中获取流得到Bitmap，并存入磁盘缓存
     */
    private Bitmap loadBitmapFromHttp(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("不要在UI线程访问网络");
        }
        if (mDiskLruCache == null) {
            return null;
        }

        String key = hashKeyFormUrl(url);
        //根据key值，来获取Editor对象
        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
        if (editor != null) {
            //获取输出流对象
            OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
            if (downloadUrlToStream(url, outputStream)) {
                editor.commit();
            } else {
                editor.abort();
            }
            mDiskLruCache.flush();
        }
        //downloadUrlToStream中写入以后磁盘缓存，再从磁盘缓存中取
        return loadBitmapFromDiskCache(url, reqWidth, reqHeight);
    }

    /**
     * 网络请求的输出流写入磁盘缓存中
     */
    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (IOException e) {
            Log.e("Ian", "downloadUrlToStream : " + e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 从磁盘缓存中获取Bitmap
     */
    private Bitmap loadBitmapFromDiskCache(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w("Ian", "不建议从UI线程读取bitmap");
        }
        if (mDiskLruCache == null) {
            return null;
        }

        Bitmap bitmap = null;
        String key = hashKeyFormUrl(url);
        DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
        if (snapShot != null) {
            //获取输入流
            FileInputStream fileInputStream = (FileInputStream) snapShot.getInputStream(DISK_CACHE_INDEX);
            //根据输入流获取mImageResizer对象需要用到的FileDescriptor对象
            FileDescriptor fileDescriptor = fileInputStream.getFD();
            bitmap = mImageResizer.decodeSampleBitmapFromFileDescriptor(fileDescriptor, reqWidth, reqHeight);
            if (bitmap != null) {
                addBitmapToMemoryCache(key, bitmap);
            }
        }

        return bitmap;
    }

    /**
     * 缓存中获取Bitmap
     */
    private Bitmap loadBitmapFromMemCache(String url) {
        String key = hashKeyFormUrl(url);
        return getBitmapFromMemCache(key);
    }

    /**
     * 根据key获取缓存中的Bitmap
     */
    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    /**
     * 根据key，将bitmap存入内存缓存
     */
    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 根据url，转换成md5的key
     */
    private String hashKeyFormUrl(String url) {
        String cacheKey;
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 获取File有多少容量
     */
    private long getUsableSpace(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        StatFs statFs = new StatFs(path.getPath());
        return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
    }

    /**
     * 创建缓存文件
     */
    private File getDiskCacheDir(Context context, String uniqueName) {
        boolean externalStorageAvailable = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        String cachePath;
        if (externalStorageAvailable) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private static class LoaderResult {
        public ImageView imageView;
        public String url;
        public Bitmap bitmap;

        public LoaderResult(ImageView imageView, String url, Bitmap bitmap) {
            this.imageView = imageView;
            this.url = url;
            this.bitmap = bitmap;
        }
    }
}
