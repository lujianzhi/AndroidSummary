package com.example.lawson.androidsummery.bitmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.bitmap.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BitmapAndCacheActivity extends AppCompatActivity {

    private TextView tipTv;
    private GridView gridView;
    private List<String> mUrlList = new ArrayList<>();
    private ImageLoader imageLoader;
    private int mImageWidth = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_and_cache);

        tipTv = findViewById(R.id.tip);
        gridView = findViewById(R.id.grid_view);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testLruCache();
            }
        });

        imageLoader = ImageLoader.build(this);
        addPicUrls();

        MyAdapter myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);
    }

    private void addPicUrls() {
        String[] imageUrls = {
                "http://b.hiphotos.baidu.com/zhidao/pic/item/a6efce1b9d16fdfafee0cfb5b68f8c5495ee7bd8.jpg",
                "http://pic47.nipic.com/20140830/7487939_180041822000_2.jpg",
                "http://pic41.nipic.com/20140518/4135003_102912523000_2.jpg",
                "http://img2.imgtn.bdimg.com/it/u=1133260524,1171054226&fm=21&gp=0.jpg",
                "http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg",
                "http://pic42.nipic.com/20140618/9448607_210533564001_2.jpg",
                "http://pic10.nipic.com/20101027/3578782_201643041706_2.jpg",
                "http://picview01.baomihua.com/photos/20120805/m_14_634797817549375000_37810757.jpg",
                "http://img2.3lian.com/2014/c7/51/d/26.jpg",
                "http://img3.3lian.com/2013/c1/34/d/93.jpg",
                "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
                "http://picview01.baomihua.com/photos/20120917/m_14_634834710114218750_41852580.jpg",
                "http://cdn.duitang.com/uploads/item/201311/03/20131103171224_rr2aL.jpeg",
                "http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1210/17/c1/spcgroup/14468225_1350443478079_1680x1050.jpg",
                "http://pic41.nipic.com/20140518/4135003_102025858000_2.jpg",
                "http://www.1tong.com/uploads/wallpaper/landscapes/200-4-730x456.jpg",
                "http://pic.58pic.com/58pic/13/00/22/32M58PICV6U.jpg",
                "http://picview01.baomihua.com/photos/20120629/m_14_634765948339062500_11778706.jpg",
                "http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=429e7b1b92ef76c6d087f32fa826d1cc/7acb0a46f21fbe09cc206a2e69600c338744ad8a.jpg",
                "http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg",
                "http://cdn.duitang.com/uploads/item/201405/13/20140513212305_XcKLG.jpeg",
                "http://photo.loveyd.com/uploads/allimg/080618/1110324.jpg",
                "http://img4.duitang.com/uploads/item/201404/17/20140417105820_GuEHe.thumb.700_0.jpeg",
                "http://cdn.duitang.com/uploads/item/201204/21/20120421155228_i52eX.thumb.600_0.jpeg",
                "http://img4.duitang.com/uploads/item/201404/17/20140417105856_LTayu.thumb.700_0.jpeg",
                "http://img04.tooopen.com/images/20130723/tooopen_20530699.jpg",
                "http://www.qjis.com/uploads/allimg/120612/1131352Y2-16.jpg",
                "http://pic.dbw.cn/0/01/33/59/1335968_847719.jpg",
                "http://a.hiphotos.baidu.com/image/pic/item/a8773912b31bb051a862339c337adab44bede0c4.jpg",
                "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
                "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
                "http://pic.58pic.com/58pic/11/25/04/91v58PIC6Xy.jpg",
                "http://img3.3lian.com/2013/c2/32/d/101.jpg",
                "http://pic25.nipic.com/20121210/7447430_172514301000_2.jpg",
                "http://img02.tooopen.com/images/20140320/sy_57121781945.jpg",
                "http://www.renyugang.cn/emlog/content/plugins/kl_album/upload/201004/852706aad6df6cd839f1211c358f2812201004120651068641.jpg"
        };
        for (String url : imageUrls) {
            mUrlList.add(url);
        }
    }

    private void testLruCache() {
        LruCache<Integer, String> lruCache = new LruCache<>(2);
        lruCache.put(1, "我是缓存一");
        lruCache.put(2, "我是缓存二");
        lruCache.put(3, "我是缓存三");
        lruCache.put(4, "我是缓存四");

        StringBuilder stringBuilder = new StringBuilder();

        String data1 = lruCache.get(5);
        if (data1 != null) {
            stringBuilder.append(data1);
        } else {
            stringBuilder.append("lruCache没有key=5的值");
        }

        stringBuilder.append(" ; ");

        String data2 = lruCache.get(3);
        if (data2 != null) {
            stringBuilder.append(data2);
        } else {
            stringBuilder.append("lruCache没有key=3的值");
        }

        tipTv.setText(stringBuilder.toString());
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mUrlList.size();
        }

        @Override
        public String getItem(int position) {
            return mUrlList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bitmap_cache_grid_view, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.imageView = convertView.findViewById(R.id.image_view);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            imageLoader.bindBitmap(getItem(position), viewHolder.imageView, mImageWidth, mImageWidth);

            return convertView;
        }

        class ViewHolder {
            ImageView imageView;
        }
    }
}
