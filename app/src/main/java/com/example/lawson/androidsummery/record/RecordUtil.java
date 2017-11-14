package com.example.lawson.androidsummery.record;

import android.content.Context;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 录音工具类
 *
 * @author lujianzhi
 * @className RecordUtil
 * @date 2017/11/14 11:14
 **/
public class RecordUtil {

    private static final int MAX_RECORD_TIME = 180;
    private Context mContext;
    private MediaRecorder mMediaRecorder;
    private RecordInfo mRecordInfo;
    private Executor mExecutor = Executors.newSingleThreadExecutor();
    private long startTime;

    private String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record_temp";
    private String recordPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record";

    private onAmplitudeListener mOnAmplitudeListener;
    private IOnCompleteListener completeListener;

    private Timer mTimer;
    private long lastEndTime;
    private Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (mMediaRecorder == null)
                    return;
                onAmplitudeListener listener = getListener();
                if (listener == null) {
                    return;
                }
                listener.isAmplitude((Boolean) msg.obj);
            } else if (msg.what == 2) {
                if (completeListener != null && mRecordInfo != null) {
                    completeListener.onComplete(mRecordInfo.getFullPath());
                    completeListener = null;
                    mOnAmplitudeListener = null;
                }

            } else {
                Toast.makeText(mContext, "录音时间过短", Toast.LENGTH_SHORT).show();
                completeListener = null;
                mOnAmplitudeListener = null;
            }
        }
    };
    private boolean isRecording = false;

    public void init(Context context) {
        mContext = context;

    }

    private void clear() {
        if (mMediaRecorder != null) {
            Log.i("Ian", "clear.... ");
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            isRecording = false;
        }
    }

    private void prepare() {
        Log.i("Ian", "prepare.... ");
        clear();
        try {
            initReocrd();
        } catch (Exception e) {
            e.printStackTrace();
            clear();
        }
    }

    private void initReocrd() throws IllegalStateException, IOException {
        mMediaRecorder = new MediaRecorder();// new出MediaRecorder对象
        mMediaRecorder.reset();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 设置MediaRecorder的音频源为麦克风
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
        // 设置MediaRecorder录制的音频格式
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mMediaRecorder.setMaxDuration(MAX_RECORD_TIME * 1000);
        mMediaRecorder.setOnInfoListener(new OnInfoListener() {

            @Override
            public void onInfo(MediaRecorder mr, int what, int extra) {
                if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                    endRecord();
                }
            }
        });
        mMediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
            @Override
            public void onError(MediaRecorder mr, int what, int extra) {

            }
        });
        // 设置MediaRecorder录制音频的编码为amr.貌似android就支持amr编码。
        File file = new File(mRecordInfo.getFullTempPath());
        Log.i("Ian", "temp path getFullTempPath" + mRecordInfo.getFullTempPath());
        if (file.exists()) {
            Log.i("Ian", "temp file isExist delete");
            file.delete();
        }
        mMediaRecorder.setOutputFile(mRecordInfo.getFullTempPath());
        mMediaRecorder.prepare();
    }

    private void start() {
        if (mMediaRecorder != null) {
            Log.i("Ian", "start.... ");
            mMediaRecorder.start();
        }
    }

    private void stop() {
        if (mMediaRecorder != null && isRecording) {
            Log.i("Ian", "stop.... ");
            Log.i("Ian", "starttime .... " + startTime);
            Log.i("Ian", "nowTime.... " + System.currentTimeMillis());
            endMessageDelayed();
            mMediaRecorder.setOnInfoListener(null);
            mMediaRecorder.setOnErrorListener(null);
            mMediaRecorder.stop();
            lastEndTime = System.currentTimeMillis();
            double soundTime = (double) (System.currentTimeMillis() - startTime) / (double) 1000;
            if (soundTime > 1.5f) {
                saveFile();
                if (completeListener != null) {
                    handler.sendEmptyMessage(2);
                }
            } else {
                handler.sendEmptyMessage(1);
            }
            clear();
        }
    }

    private void saveFile() {
        if (mRecordInfo != null) {
            File tempFile = new File(mRecordInfo.getFullTempPath());
            if (tempFile.exists()) {
                tempFile.renameTo(new File(mRecordInfo.getFullPath()));
                Log.i("Ian", "file renameTo getFullPath  " + mRecordInfo.getFullPath());
            }
        }
    }

    private onAmplitudeListener getListener() {
        if (mOnAmplitudeListener != null) {
            return mOnAmplitudeListener;
        }
        return null;
    }

    public void setOnAmplitude(final onAmplitudeListener mOnAmplitude) {
//		this.mOnAmplitudeListener = new WeakReference<onAmplitudeListener>(mOnAmplitude);
        //略做延时，确保mOnAmplitudeListener.get()不为空
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                delay(mOnAmplitude);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1500);
    }

    public IOnCompleteListener getCompleteListener() {
        return completeListener;
    }

    public void setiOnCompleteListener(IOnCompleteListener completeListener) {
        this.completeListener = completeListener;
    }

    private void delay(onAmplitudeListener mOnAmplitude) {
        this.mOnAmplitudeListener = mOnAmplitude;
    }

    /**
     * 描述: 开始录音 2次录音时间差距必须在2秒以上
     *
     * @return boolean
     * @author louwj
     * @created 2015-3-13 下午2:45:48
     */
    public boolean startRecord() {
        if (isRecording || System.currentTimeMillis() - lastEndTime < 2000) {
            Toast.makeText(mContext, "录音正在准备中，请重试", Toast.LENGTH_SHORT).show();
            Log.i("Ian", "————————————————华丽的分割线222——————————————————");
            return false;
        }
        Log.i("Ian", "————————————————华丽的分割线——————————————————");
        Log.i("Ian", "startRecord.... " + isRecording);
        mExecutor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    synchronized (RecordUtil.this) {
                        prepare();
                        startTime = System.currentTimeMillis();
                        isRecording = true;
                        start();
                        startMessageDelayed();
                    }
                } catch (Exception e) {
                    clear();
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

    public void endRecord() {
        Log.i("Ian", "endRecord.... " + isRecording);
        if (isRecording) {
            mExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    synchronized (RecordUtil.this) {
                        try {
                            stop();
                        } catch (Exception e) {
                            clear();
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void endRecordWithoutSaving() {
        if (isRecording) {
            mExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    synchronized (RecordUtil.class) {
                        try {
                            if (mMediaRecorder != null && isRecording) {
                                File file = new File(mRecordInfo.getFullTempPath());
                                if (file.exists()) {
                                    file.delete();
                                }
                                endMessageDelayed();
                                mMediaRecorder.stop();
                                clear();
                            }
                        } catch (Exception e) {
                            clear();
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    /**
     * 只是单纯为检测声音波动准备
     */
    public void startDetect() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    clear();
                    File temp = new File(tempPath + "justDetectAmplitude.amr");
                    mMediaRecorder = new MediaRecorder();
                    mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
                    mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mMediaRecorder.setOutputFile(temp.getAbsolutePath());
                    mMediaRecorder.prepare();
                    isRecording = true;
                    mMediaRecorder.start();
                    startMessageDelayed();
                } catch (IOException e) {
                    e.printStackTrace();
                    clear();
                }
            }
        });
    }

    /**
     * 结束检测声音
     */
    public void endDetect() {
        if (isRecording) {
            mExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    synchronized (RecordUtil.class) {
                        try {
                            if (mMediaRecorder != null && isRecording) {
                                File temp = new File(tempPath + "justDetectAmplitude.amr");
                                if (temp.exists()) {
                                    temp.delete();
                                }
                                endMessageDelayed();
                                mMediaRecorder.stop();
                                clear();
                            }
                        } catch (Exception e) {
                            clear();
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void endMessageDelayed() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void startMessageDelayed() {
        endMessageDelayed();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                synchronized (RecordUtil.this) {
                    if (mMediaRecorder != null && isRecording) {
                        boolean isAmplitude;
                        if (mMediaRecorder.getMaxAmplitude() > 1000) {
                            isAmplitude = true;
                        } else {
                            isAmplitude = false;
                        }
                        Message message = handler.obtainMessage();
                        message.obj = isAmplitude;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            }
        }, 0, 1 * 100);
    }

    public boolean setRecord(RecordInfo recordInfo) {
        mRecordInfo = null;
        if (!isRecording) {
            mRecordInfo = recordInfo;
            return true;
        }
        return false;
    }

    public RecordInfo buildRecordInfo(String name) {
        return buildRecordInfo(name, 0);
    }

    public RecordInfo buildRecordInfo(String name, long recordId) {
        RecordInfo recordInfo = new RecordInfo();
        recordInfo.path = recordPath;
        recordInfo.tempPath = tempPath;
        recordInfo.name = name;
        recordInfo.recordId = recordId;
        return recordInfo;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void setRecording(boolean isRecording) {
        this.isRecording = isRecording;
    }

    public class RecordInfo {
        public long recordId;
        public String path;
        public String tempPath;
        public String name;

        public String getFullPath() {
            return path + "/" + name + ".amr";
        }

        public String getFullTempPath() {
            return tempPath + "/" + name + ".amr";
        }
    }

    public RecordInfo getRecordInfo() {
        return mRecordInfo;
    }

    public interface onAmplitudeListener {

        /**
         * 描述:是否有语音输入
         *
         * @param flag
         * @return void
         * @author louwj
         * @created 2014-10-16 下午4:23:28
         * @since v1.0.0
         */
        public void isAmplitude(boolean flag);

    }

    public interface IOnCompleteListener {

        /**
         * 发现有时候EventBus接收到消息以后不能更新UI的情况，
         * 此接口为了弥补这个不足。
         */
        void onComplete(String path);
    }
}
