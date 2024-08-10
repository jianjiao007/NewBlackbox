package com.jianjiao.bx;

import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import com.jianjiao.bx.node.AccUtils;
import com.jianjiao.bx.node.GlobalVariableHolder;

public class MyAccessibilityService extends AccUtils {

    public MyAccessibilityService() {
    }

    /**
     * 监听事件的发生
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        // 刷新当前 Activity()
        super.refreshCurrentActivity(accessibilityEvent);

        // 监听点击事件
        super.systemClickListener(accessibilityEvent);
    }

    @Override
    public boolean onKeyEvent(KeyEvent event) {
        Log.d(MyGlobalVar.TAG, "监听到音量键:" + event.getKeyCode());
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Log.d(MyGlobalVar.TAG, "任务状态:" + GlobalVariableHolder.isRunning);
            if (GlobalVariableHolder.isRunning) {
                GlobalVariableHolder.isRunning = false;
                Log.d(MyGlobalVar.TAG, "停止运行:" + false);
                return true;
            }
        }
        return super.onKeyEvent(event);
    }
}