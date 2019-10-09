package com.example.lawson.androidsummery.securitycheck;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.LocalServerSocket;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * 多开检测工具类
 */
public class MultiAppCheckUtils {
    private MultiAppCheckUtils() {
    }

    /**
     * 维护一份市面多开应用的包名列表
     */
    private static String[] virtualPkgs = {
            "com.bly.dkplat",//多开分身本身的包名
//            "dkplugin.pke.nnp",//多开分身克隆应用的包名会随机变换
            "com.by.chaos",//chaos引擎
            "com.lbe.parallel",//平行空间
            "com.excelliance.dualaid",//双开助手
            "com.lody.virtual",//VirtualXposed，VirtualApp
            "com.qihoo.magic"//360分身大师
    };

    /**
     * 通过检测app私有目录，多开后的应用路径会包含多开软件的包名
     *
     * @param context context
     * @return 是否多开
     */
    public static boolean checkByPackagePath(Context context) {
        String path = context.getFilesDir().getPath();
        for (String virtualPkg : virtualPkgs) {
            if (path.contains(virtualPkg)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检测原始的包名，多开应用会hook处理getPackageName方法
     * 顺着这个思路，如果在应用列表里出现了同样的包，那么认为该应用被多开了
     *
     * @param context context
     * @return 是否多开
     */
    public static boolean checkByOriginApkPackageName(Context context) {
        try {
            if (context == null)
                throw new IllegalArgumentException("you have to set context first");
            int count = 0;
            String packageName = context.getPackageName();
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> pkgs = pm.getInstalledPackages(0);
            for (PackageInfo info : pkgs) {
                if (packageName.equals(info.packageName)) {
                    count++;
                }
            }
            return count > 1;
        } catch (Exception ignore) {
        }
        return false;
    }

    /**
     * 运行被克隆的应用，该应用会加载多开应用的so库
     * 检测已经加载的so里是否包含这些应用的包名
     *
     * @return 是否多开
     */
    public static boolean checkByMultiApkPackageName() {
        BufferedReader bufr = null;
        try {
            bufr = new BufferedReader(new FileReader("/proc/self/maps"));
            String line;
            while ((line = bufr.readLine()) != null) {
                for (String pkg : virtualPkgs) {
                    if (line.contains(pkg)) {
                        return true;
                    }
                }
            }
        } catch (Exception ignore) {

        } finally {
            if (bufr != null) {
                try {
                    bufr.close();
                } catch (IOException e) {

                }
            }
        }
        return false;
    }

    /**
     * Android系统一个app一个uid
     * 如果同一uid下有两个进程对应的包名，在"/data/data"下有两个私有目录，则该应用被多开了
     *
     * @return 是否多开
     */
    public static boolean checkByHasSameUid() {
        String filter = CommandUtil.getUidStrFormat();
        if (TextUtils.isEmpty(filter)) return false;

        String result = CommandUtil.exec("ps");
        if (TextUtils.isEmpty(result)) return false;

        String[] lines = result.split("\n");
        if (lines == null || lines.length <= 0) return false;

        int exitDirCount = 0;

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains(filter)) {
                int pkgStartIndex = lines[i].lastIndexOf(" ");
                String processName = lines[i].substring(pkgStartIndex <= 0
                                                                ? 0 : pkgStartIndex + 1, lines[i].length());
                File dataFile = new File(String.format("/data/data/%s", processName, Locale.CHINA));
                if (dataFile.exists()) {
                    exitDirCount++;
                }
            }
        }
        return exitDirCount > 1;
    }

    private static volatile LocalServerSocket localServerSocket;

    /**
     * 如issue25讨论
     * https://github.com/lamster2018/EasyProtector/issues/25
     * 感谢https://github.com/wangkunlin提供
     * <p>
     * 狭义多开：只要app是通过多开软件打开的，则认为多开，即使同一时间内只运行了一个app
     * 广义多开：无论app是否运行在多开软件上，只要app在运行期间，有其余的『自己』在运行，则认为多开
     *
     * @param uniqueMsg uniqueMsg
     * @return 是否是广义上的多开
     */
    public static boolean checkByCreateLocalServerSocket(String uniqueMsg) {
        if (localServerSocket != null) return false;
        try {
            localServerSocket = new LocalServerSocket(uniqueMsg);
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}
