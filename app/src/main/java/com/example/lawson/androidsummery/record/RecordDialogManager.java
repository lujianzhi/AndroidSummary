package com.example.lawson.androidsummery.record;

import android.content.Context;
import android.text.TextUtils;

import com.example.lawson.androidsummery.record.dialog.RecordDialog;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.fragment.app.FragmentManager;

/**
 * 录音管理类
 *
 * @author lujianzhi
 * @className RecordDialogManager
 * @date 2017/11/14 11:15
 **/
public class RecordDialogManager {

    private static volatile RecordDialogManager instance;
    private RecordDialog recordDialog;
    private RecordUtil recordUtil;
    //录音文件名
    private String fileName;

    private ExecutorService singleThreadExecutor;

    private RecordDialogManager() {
        recordUtil = new RecordUtil();
        recordDialog = new RecordDialog();
        singleThreadExecutor = Executors.newSingleThreadExecutor();
        initRecord();
    }

    public static RecordDialogManager getInstance() {
        if (instance == null) {
            synchronized (RecordDialogManager.class) {
                if (instance == null) {
                    instance = new RecordDialogManager();
                }
            }
        }
        return instance;
    }

    /**
     * 显示录音对话框
     *
     * @param fragmentManager 需要v4包中的FragmentManager
     */
    public void showRecordDialog(FragmentManager fragmentManager) {
        if (recordDialog == null) {
            recordDialog = new RecordDialog();
        }
        recordDialog.show(fragmentManager, "record_dialog");
    }

    /**
     * 1.初始化录音
     */
    private void initRecord() {
        recordUtil.setRecord(recordUtil.buildRecordInfo(getFileName()));
        recordUtilSetOnAmplitude(onAmplitudeListener);
        recordUtilSetIOnCompleteListener(onCompleteListener);

        recordDialog.setRecordDialogClickListener(recordDialogClickListener);
    }

    /**
     * 2.开始录音
     * 外部调用的入口
     */
    public void startRecording(FragmentManager fragmentManager, Context context) {
        recordUtil.init(context);
        if (recordUtil.startRecord()) {
            showRecordDialog(fragmentManager);
        }
    }

    /**
     * 3-1.结束录音
     * 保存文件
     */
    private void endRecord() {
        recordUtil.endRecord();
    }

    /**
     * 3-2.结束录音
     * 不保存文件
     */
    private void endRecordWithoutSaving() {
        recordUtil.endRecordWithoutSaving();
    }

    /**
     * 4.删除录音文件
     */
    public void deleteRecordFile() {
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                deleteRecordFile(recordUtil.getRecordInfo().getFullPath());
            }
        });
    }

    private void deleteRecordFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 获取文件名
     *
     * @return 录音文件名
     */
    private String getFileName() {
        String name = System.currentTimeMillis() + "";
        if (!TextUtils.isEmpty(fileName)) {
            name = String.valueOf(fileName);
        }
        return name;
    }

    /**
     * 设置实时声音回调
     * 外部可指定自己的回调
     *
     * @param onAmplitudeListener 指定回调
     */
    public void recordUtilSetOnAmplitude(RecordUtil.onAmplitudeListener onAmplitudeListener) {
        recordUtil.setOnAmplitude(onAmplitudeListener);
    }

    /**
     * 设置完成录音后的回调
     * 外部可指定自己的回调
     *
     * @param onCompleteListener 指定回调
     */
    public void recordUtilSetIOnCompleteListener(RecordUtil.IOnCompleteListener onCompleteListener) {
        recordUtil.setiOnCompleteListener(onCompleteListener);
    }

    private RecordDialog.IRecordDialogClickListener recordDialogClickListener = new RecordDialog.IRecordDialogClickListener() {
        @Override
        public void cancel() {
            endRecordWithoutSaving();
        }

        @Override
        public void confirm() {
            endRecord();
        }
    };

    private RecordUtil.onAmplitudeListener onAmplitudeListener = new RecordUtil.onAmplitudeListener() {
        @Override
        public void isAmplitude(boolean flag) {
            //检测是否有声音输入，实现根据声音输入来进行声波变化动画
        }
    };

    private RecordUtil.IOnCompleteListener onCompleteListener = new RecordUtil.IOnCompleteListener() {
        @Override
        public void onComplete(String path) {
            //上传音频文件
            //以及界面UI显示
        }
    };

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
