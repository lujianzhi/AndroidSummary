package com.example.lawson.androidsummery.diyview.timer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ian.Lu on 2017/9/27.
 * Project : AndroidSummary
 */

public class TimerManager {
    //正计时
    public static final int CLOCKWISE = 1;
    //倒计时
    public static final int ANTICLOCKWISE = 2;

    private Handler handler;
    private TextView timerTv;
    //计时方式
    private int timerForm;
    //计时开始时间
    private long startTime;
    //计时间隔，默认1000毫秒
    private long timerDuration = 1000;

    private class TimerHandler extends Handler {

        TimerHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == CLOCKWISE) {
                startTime += 1000;
            } else if (msg.what == ANTICLOCKWISE) {
                startTime -= 1000;
            }
            timerTv.setText(transformTimeString(startTime));
            sendEmptyMessageDelayed(msg.what, timerDuration);
        }
    }

    public TimerManager(TextView timerTv, int timerForm, long startTime) {
        this.timerTv = timerTv;
        this.timerForm = timerForm;
        this.startTime = startTime;
        handler = new TimerHandler(Looper.getMainLooper());
    }

    /**
     * 开始计时
     */
    public void startTiming() {
        handler.sendEmptyMessageDelayed(timerForm, timerDuration);
    }

    /**
     * 设置计时的间隔，单位毫秒
     *
     * @param timerDuration 时间间隔
     */
    public void setTimerDuration(long timerDuration) {
        this.timerDuration = timerDuration;
    }

    /**
     * 将long类型时间转换为mm:ss格式
     *
     * @param time long类型时间
     * @return String类型时间
     */
    private String transformTimeString(long time) {
        SimpleDateFormat format2 = new SimpleDateFormat("mm:ss", Locale.getDefault());
        Date date3 = new Date(time);
        return format2.format(date3);
    }

    /**
     * 移除handler中的任务，防止handler泄露
     */
    private void releaseHandler() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }

    /**
     * 释放TimerManager中的数据
     */
    public void releaseTimer() {
        releaseHandler();
    }
}
