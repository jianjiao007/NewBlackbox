package com.jianjiao.bx;

import static com.jianjiao.bx.node.AccUtils.printLogMsg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jianjiao.bx.node.okhttp3.HttpUtils;


public class MyBoard extends BroadcastReceiver {
    public static String tempData = "";
    private static final String TAG = MyGlobalVar.TAG;

    public static void uploadData(String data) {
        if (tempData.equals(data)) {
            return;
        }
        tempData = data;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (data.length() > 0) {
                    String res = HttpUtils.get("http://39.107.121.128:9998/up?t=" + data);
                    printLogMsg("上传数据:" + res);
                    Log.d(TAG, "uploadData: " + res);
                }
            }
        }).start();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 检查接收到的Intent的action是否是我们期望的
        if (intent != null && "com.jianjiao.test.PDDGUANGBO".equals(intent.getAction())) {
            // 从Intent中获取额外的数据
            int code = intent.getIntExtra("code", 0);
            int userId = intent.getIntExtra("userId", 0);
            String data = intent.getStringExtra("data");
            MyGlobalVar.intent = intent;
            Log.d(TAG, "接收到的数据: " + code + "|" + data + "|" + userId);

            switch (code) {
                case 10:
                    //上传信息
                    uploadData(data);

            }
        }
    }
}