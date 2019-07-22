package com.example.lawson.androidsummery.securitycheck;

import android.content.Context;

/**
 * 聚合检测工具类
 */
public class SecurityCheckUtils {

    private SecurityCheckUtils() {
    }

    /**
     * Xposed框架是否存在
     *
     * @return Xposed框架是否存在
     */
    public static boolean checkXposed() {
        return XposedCheckUtils.isXposedExists() || XposedCheckUtils.isXposedExistByThrow();
    }

    /**
     * 多开检测
     * <p>
     * checkByCreateLocalServerSocket()的多开是广义上的多开，即第二个app(不管他是复制的还是原来的)打开时，认为第二个app是多开的；
     * 该方法可以做到通杀所有双开app。
     * <p>
     * 其余的局限性很大
     *
     * @param context context
     * @return 是否多开
     */
    public static boolean checkMultiApp(Context context) {
        return MultiAppCheckUtils.checkByPackagePath(context) ||
               MultiAppCheckUtils.checkByOriginApkPackageName(context) ||
               MultiAppCheckUtils.checkByMultiApkPackageName() ||
               MultiAppCheckUtils.checkByHasSameUid() ||
               MultiAppCheckUtils.checkByCreateLocalServerSocket("lss");
    }

    /**
     * 虚拟机检测
     *
     * @param context 上下文
     * @return 是否是虚拟机
     */
    public static boolean checkVM(Context context) {
        return VMCheckUtils.readSysProperty(context);
    }

    /**
     * Root检测
     *
     * @return 是否root
     */
    public static boolean checkRoot() {
        return RootCheckUtils.checkRoot();
    }
}
