package com.example.lawson.androidsummery.thread;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ian.Lu on 2017/9/13.
 * Project : AndroidSummary
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {


    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    //防止Context的内存泄露，这里用applicationContext
    private Context applicationContext;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context applicationContext) {
        //获取默认的异常处理器
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.applicationContext = applicationContext;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            //将错误信息写入本地
            dumpExceptionToSDCard(t, e);
        } catch (IOException | PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();

        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (defaultUncaughtExceptionHandler != null) {
            defaultUncaughtExceptionHandler.uncaughtException(t, e);
        } else {
            Process.killProcess(Process.myPid());
        }

    }

    private void dumpExceptionToSDCard(Thread t, Throwable e) throws IOException, PackageManager.NameNotFoundException {
        File fileDir = new File(Environment.getExternalStorageDirectory() + File.separator + "ian_crash");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm\n", Locale.CHINA);
        Date date = new Date(System.currentTimeMillis());
        File file = new File(fileDir.getAbsolutePath() + File.separator + dateFormat.format(date) + "crash.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        //为了将具体的代码信息输出，这里需要用到printStackTrace(PrintWriter s)
        //所以这里用PrintWriter
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        printWriter.println(dateFormat.format(date));
        String errorStr = "ThreadName : " + t.getName();
        printWriter.println(errorStr);

        PackageManager packageManager = applicationContext.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(applicationContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        String appVersionStr = "应用版本:" + packageInfo.versionName + "_" + packageInfo.versionCode;
        printWriter.println(appVersionStr);

        String osVersionStr = "系统版本:" + Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT;
        printWriter.println(osVersionStr);

        String vendorStr = "手机制造商:" + Build.MANUFACTURER;
        printWriter.println(vendorStr);

        String modelStr = "手机型号:" + Build.MODEL;
        printWriter.println(modelStr);

        String cpuStr = "CPU架构:" + Build.CPU_ABI;
        printWriter.println(cpuStr);

        e.printStackTrace(printWriter);
        printWriter.println();
        printWriter.close();
    }
}
