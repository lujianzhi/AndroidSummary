package com.example.lawson.androidsummery.securitycheck;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Root检测工具类
 */
public class RootCheckUtils {

    private RootCheckUtils() {
    }

    /**
     * 检查手机是否root
     * 首先判断是否有su文件，然后执行su文件，
     * 执行成功代表手机已经root，执行不成功，不一定代表手机已经root（原因在checkExecuteSu()方法里已经说明）
     *
     * @return 是否root
     */
    public static boolean checkRoot() {
        return checkSuExecutable(checkSuFileByPath() != null ? checkSuFileByPath() : checkSuFileByWhich());
    }

    /**
     * 检查是否存在执行权限
     *
     * @param suFile su文件
     * @return 是否可执行
     */
    private static boolean checkSuExecutable(File suFile) {
        String suFilePath;
        if (suFile != null && suFile.exists()) {
            suFilePath = suFile.getAbsolutePath();
        } else {
            return false;
        }
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ls -l " + suFilePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String permission = in.readLine();
            if (!TextUtils.isEmpty(permission) || permission.length() >= 4) {
                char flag = permission.charAt(3);
                if ('s' == flag || 'x' == flag) {
                    return checkExecuteSu();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    /**
     * 执行su文件
     *
     * @return 是否执行成功
     */
    private static boolean checkExecuteSu() {
        Process process = null;
        DataOutputStream os = null;
        try {
            //如果手机安装了root权限管理软件，那么执行这句话时会弹框提示用户是否给当前app授予root权限
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            /*
             * exitValue为0时，代表手机已经有root权限；
             * 如果不为0，1.当前手机没有被root；2.当前手机已经root，但是当前应用没有root权限
             *
             * 举例：
             * kingRoot在设备root但是没有授权给应用时，返回值是固定的为0；
             * 但是SuperSU，如果在拒绝后返回值为1
             */
            int exitValue = process.waitFor();
            return exitValue == 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    /**
     * 检查手机是否存在su文件
     * 方法一：根据path检查
     *
     * @return su文件
     */
    private static File checkSuFileByPath() {
        File suFile;
        String[] paths = {
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su"
        };
        for (String path : paths) {
            suFile = new File(path);
            if (suFile.exists()) {
                return suFile;
            }
        }
        //找不到直接return null
        return null;
    }

    /**
     * 检查手机是否存在su文件
     * 方法二：运行which检查
     *
     * @return 是否root
     */
    private static File checkSuFileByWhich() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            File file = null;
            if (!TextUtils.isEmpty(in.readLine())) {
                file = new File(in.readLine());
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
