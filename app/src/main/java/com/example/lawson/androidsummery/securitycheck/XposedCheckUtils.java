package com.example.lawson.androidsummery.securitycheck;

import java.lang.reflect.Field;

/**
 * Xposed检测工具类
 * 判断xposed的包是否存在
 * 1.是通过主动抛出异常查栈信息;
 * 2.是主动反射调用。
 */
public class XposedCheckUtils {

    private static final String XPOSED_HELPERS = "de.robv.android.xposed.XposedHelpers";
    private static final String XPOSED_BRIDGE = "de.robv.android.xposed.XposedBridge";
    private static final String XPOSED_BRIDGE_CLASSNAME = "XposedBridge";

    private XposedCheckUtils() {
    }

    /**
     * 通过检查是否已经加载了XP类来检测
     *
     * @return 是否存在xposed
     */
    public static boolean isXposedExists() {
        try {
            Object xpHelperObj = ClassLoader
                    .getSystemClassLoader()
                    .loadClass(XPOSED_HELPERS)
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            Object xpBridgeObj = ClassLoader
                    .getSystemClassLoader()
                    .loadClass(XPOSED_BRIDGE)
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 通过主动抛出异常，检查堆栈信息来判断是否存在XP框架
     *
     * @return 是否存在xposed
     */
    public static boolean isXposedExistByThrow() {
        try {
            throw new Exception("gg");
        } catch (Exception e) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                /*
                 * 这里有个诡异的现象，
                 * debug时通过stackTraceElement.getClassName()可以轮询获取到"de.robv.android.xposed.XposedBridge"
                 * 但是实际运行通过Log，在轮询到XposedBridge时，getClassName()的值是"com.android.file"!!!WTF???
                 * 所以只能通过getFileName()来进行判断
                 */
                if (stackTraceElement.getFileName().contains(XPOSED_BRIDGE_CLASSNAME)) return true;
            }
            return false;
        }
    }

    /**
     * 尝试关闭XP框架
     * 先通过isXposedExistByThrow判断有没有XP框架
     * 有的话先hookXP框架的全局变量disableHooks
     * <p>
     * 漏洞在，如果XP框架先hook了isXposedExistByThrow的返回值，那么后续就没法走了
     * 现在直接先hookXP框架的全局变量disableHooks
     *
     * @return 是否关闭成功的结果
     */
    public boolean tryShutdownXposed() {
        Field xpdisabledHooks = null;
        try {
            xpdisabledHooks = ClassLoader.getSystemClassLoader()
                                         .loadClass(XPOSED_BRIDGE)
                                         .getDeclaredField("disableHooks");
            xpdisabledHooks.setAccessible(true);
            xpdisabledHooks.set(null, Boolean.TRUE);
            return true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

}
