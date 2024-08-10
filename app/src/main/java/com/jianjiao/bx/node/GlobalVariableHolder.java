package com.jianjiao.bx.node;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caoccao.javet.interop.V8Runtime;

import java.util.HashMap;

public class GlobalVariableHolder {
    public static boolean CRON_TASK = false; // 定时任务是否开启
    public static String CRON_TASK_FILE = "定时任务配置.txt"; // 定时任务配置
    public static String CRON_TASK_FILE_TEST = "test_demo.js"; // 定时任务测试
    public static boolean DEV_MODE = false; // FATJS 的开发者模式
    public static V8Runtime v8Runtime;
    @SuppressLint("SdCardPath")
    public static final String ABSOLUTE_PATH = "/sdcard/fatjs/";
    public static final String PATH = "/fatjs/";
    public static String FATJS_INFO = "\n\n\n\n\n\n\n\n\n\nauthor: 林笙\n\nwx: FATJS_Lin\n\nGitHub: FATJS";
    public static String ANDROID_ID = "";
    public static String pseudoID = "";
    public static String guid = "";
    public static String OAID = "";
    public static String PHONE_NAME = "";

    // 悬浮窗展示的文字
    public static TextView btnTextView = null;
    // 上下文
    public static Context context = null;
    public static Activity mainActivity = null;
    // ll
    public static LinearLayout ll = null;
    public static String checkedFileName = "a.js"; // 当前选中的脚本文件名称
    // 当前ActivityName
    public static volatile String currentActivityName = "";
    public static volatile String curActivityName = "123";//自定义activityname
    // 屏幕宽高
    public static int text_size = 11;
    public static int mWidth = 1440;
    public static int mHeight = 3040;
    public static int __mHeight = -1; //去掉导航栏和状态栏的高度
    public static int statusBarHeight = -1; //状态栏的高度
    public static int navigationBarHeight = -1; //导航栏的高度
    public static boolean navigationBarOpen = true; //导航栏是否开启
    // 悬浮球位置
    public static int float_x = 0;
    public static int float_y = 0;

    public static String tag = "fatjslog";
    // 停顿时长
    public static final int waitHrefOfSecond  =   500;
    public static final int waitOneSecond     =   1000;
    public static final int waitTwoSecond     =   2000;
    public static final int waitThreeSecond   =   3000;
    public static final int waitFourSecond    =   4000;
    public static final int waitFiveSecond    =   5000;
    public static final int waitSixSecond     =   6000;
    public static boolean isStop = false; // 暂停标识
    public static boolean isRunning = false; // 是否有任务在运行
    public static boolean killThread = false; // 是否强制关闭
    public static boolean isOpenFloatWin = false; // 悬浮窗是否打开
    public static void saveConfig() {

    }

    public static void reviewConfig() {

    }
    public static HashMap<String, Object> hashMapBuffer;

    public static void showMyLog(String str) {
        Log.d(tag, str);
    }



    public static void initDisplay() {
        DisplayMetrics dm = new DisplayMetrics();//屏幕度量
        Display defaultDisplay = mainActivity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getRealMetrics(dm);
        mWidth = dm.widthPixels;//宽度
        mHeight = dm.heightPixels;//高度
        DisplayMetrics __dm = new DisplayMetrics();//屏幕度量
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(__dm);
        __mHeight = __dm.heightPixels;//去掉导航栏和状态栏的高度
        statusBarHeight = getStatusBarHeight(); //状态栏的高度
        navigationBarHeight = getNavigationBarHeight(); //导航栏的高度
        if (__mHeight + navigationBarHeight == mHeight) { // 屏幕内是否有导航栏的高度
            navigationBarOpen = true;
            return;
        }
        if (__mHeight + statusBarHeight + navigationBarHeight == mHeight) {
            navigationBarOpen = true;
            return;
        }
        if (__mHeight + statusBarHeight == mHeight) {
            navigationBarOpen = false;
            return;
        }
        if (__mHeight + navigationBarHeight > mHeight) {
            navigationBarOpen = false;
            return;
        }
        navigationBarOpen = false;
    }

    public static int getNavigationBarHeight() {
        int navigationBarHeight = 0;
        int resourceId = mainActivity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = mainActivity.getResources().getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }

    public static int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = mainActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = mainActivity.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

}
