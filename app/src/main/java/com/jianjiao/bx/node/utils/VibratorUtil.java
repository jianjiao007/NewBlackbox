package com.jianjiao.bx.node.utils;

import static com.jianjiao.bx.node.GlobalVariableHolder.context;

import android.content.Context;
import android.os.Vibrator;

public class VibratorUtil {
    private VibratorUtil() {
    }

    /**
     * 震动
     */
    public static void startVibrator() {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(15);
    }
}