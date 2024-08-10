package com.jianjiao.bx;

import android.content.Intent;
import android.content.SharedPreferences;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyGlobalVar {
    public static String currentActivity = "";
    public static String TAG = "__尖叫__";
    public static Intent intent = null;
    public static Boolean devMode = false;
    public static Boolean isWait = false;
    public static int taskCount = 0;



    public static SharedPreferences preferences;
    public static int getIntentCode() {
        if (intent != null) {
            return intent.getIntExtra("code", 0);
        } else {
            return 0;
        }
    }

    public static String getIntentData() {
        if (intent != null) {
            return intent.getStringExtra("data");
        } else {
            return "";
        }
    }


    /**
     * 从URL中提取goods_id参数的值。
     *
     * @param urlString URL字符串
     * @return goods_id的值，如果不存在则返回null
     */
    public static String getGoodsIdForUrl(String urlString) {
        try {
            // 创建URL对象
            URL url = new URL(urlString);
            // 获取查询字符串
            String query = url.getQuery();

            // 使用正则表达式匹配goods_id参数
            Pattern pattern = Pattern.compile("goods_id=(\\d+)");
            Matcher matcher = pattern.matcher(query);

            // 如果找到匹配项，则返回第一个匹配的值
            if (matcher.find()) {
                return matcher.group(1); // 返回匹配的数字部分
            }
        } catch (Exception e) {
            // 处理可能发生的异常，例如URL格式错误
            e.printStackTrace();
        }

        // 如果没有找到goods_id或者发生异常，返回null
        return null;
    }

}
