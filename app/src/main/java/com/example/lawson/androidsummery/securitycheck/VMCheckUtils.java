package com.example.lawson.androidsummery.securitycheck;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.text.TextUtils;

/**
 * 虚拟机检测工具类
 */
public class VMCheckUtils {

    public static boolean readSysProperty(Context context) {
        if (context == null)
            throw new IllegalArgumentException("context must not be null");

        int suspectCount = 0;

        String baseBandVersion = getProperty("gsm.version.baseband");
        if (null == baseBandVersion || baseBandVersion.contains("1.0.0.0"))
            ++suspectCount;

        String buildFlavor = getProperty("ro.build.flavor");
        if (null == buildFlavor || buildFlavor.contains("vbox") || buildFlavor.contains("sdk_gphone"))
            ++suspectCount;

        String productBoard = getProperty("ro.product.board");
        if (null == productBoard || productBoard.contains("android") || productBoard.contains("goldfish"))
            ++suspectCount;

        String boardPlatform = getProperty("ro.board.platform");
        if (null == boardPlatform || boardPlatform.contains("android"))
            ++suspectCount;

        String hardWare = getProperty("ro.hardware");
        if (null == hardWare) ++suspectCount;
        else if (hardWare.toLowerCase().contains("ttvm")) suspectCount += 10;
        else if (hardWare.toLowerCase().contains("nox")) suspectCount += 10;

        boolean isSupportCameraFlash = context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
        if (!isSupportCameraFlash) ++suspectCount;

        SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sm != null) {
            int sensorSize = sm.getSensorList(Sensor.TYPE_ALL).size();
            if (sensorSize < 7) ++suspectCount;
        }


        String userApps = CommandUtil.exec("pm list package -3");
        int userAppSize = getUserAppNum(userApps);
        if (userAppSize < 5) ++suspectCount;

        String filter = CommandUtil.exec("cat /proc/self/cgroup");
        if (null == filter) ++suspectCount;

        return suspectCount > 3;
    }

    private static int getUserAppNum(String userApps) {
        if (TextUtils.isEmpty(userApps)) return 0;
        String[] result = userApps.split("package:");
        return result.length;
    }

    private static String getProperty(String propName) {
        String property = CommandUtil.getProperty(propName);
        return TextUtils.isEmpty(property) ? null : property;
    }

}
