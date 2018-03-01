package com.example.lawson.androidsummery.hencoder.ui.utils;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by Ian.Lu on 2017/12/5.
 * Project : AndroidSummary
 */

public class LogUtils {

    public static void logCharsArray(String charsName, char[] chars) {
        Log.i("Ian", charsName + " : " + Arrays.toString(chars));
    }
}
