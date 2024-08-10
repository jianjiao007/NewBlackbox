package com.jianjiao.bx.node.okhttp3;

import static com.jianjiao.bx.node.AccUtils.printLogMsg;

import android.util.Log;

import com.jianjiao.bx.MyGlobalVar;
import com.jianjiao.bx.node.utils.ExceptionUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.zip.GZIPInputStream;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpUtils {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    public static String pid = "";
    public static String ck = "";

    public static void getAsync(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void postAsync(String url, String json, Callback callback) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static String get(String url) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return ExceptionUtil.toString(e);
        }
    }

    public static String post(String url, String json) {
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return ExceptionUtil.toString(e);
        }
    }

    public static String transferUrl(String goodsUrl, String ck, String pid) {
        HttpUtils.ck = ck;
        HttpUtils.pid = pid;
        Log.d(MyGlobalVar.TAG, "transferUrl: " + goodsUrl + "|" + ck + "|" + pid);
        LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put(":authority", "jinbao.pinduoduo.com");
        headerMap.put(":method", "POST");
        headerMap.put(":path", "/network/api/promotion/transferUrl");
        headerMap.put(":scheme", "https");
        headerMap.put("Accept", "application/json, text/plain, */*");
        headerMap.put("Accept-Encoding", "gzip, deflate, br, zstd");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.9");
        headerMap.put("Anti-Content", "0asAfajyXjHgjgT9Q4JAeAi5A0vNHq-E6WWeUmWcw3IVYz_1HwfkQEVZnyyf1oOrcOyO_E3-t88vAKZYdFwKTmQPZpIxzt5klX6vtjnwToA8hPWdgpouAP6DR9J0QXBeUamT1rYfJA30uxm3cszJjjh0Jum65jx7CsSOnGsZb0R9WN9bhCaC0qQPMiagxZ3UwC8Ll4ioYqiakna2JA2yd8N0ywWb5XVNTPW9jAAbNen6h7DA2dKcCgLdBCUvb0Gc7g7oXzjN0rE9PC92R9xh9E8sSUdb1rZvW9ch9LR2YoVwJ2ZFdWDb6JagLqy2AL9282ox7cGQ0Hp_xrubu6qFRNCTLWY-E1pQ5At2mQzn_EbZUjiUdgPBzkfIsvmJ238pLYaFp3ede_T6f9BTCbT-wdLP9viOF_0O5G3W6p4JwxzjrLbkkjMjgIAtKhxhMBkMhV7tQBME4Ir75DU__fwVTbwt9_dPvPntQJFVhlE8vwzUxKvy_N62p-9onsPXUe8-1ZBXdOhepnsjFC4Rw-Med7fsx2Kr3Bgu-zDXW3imD8IEj7vdKre1T-MyMpnpIE6XjTjPHEv6kJ7yHBNSKKHLmpc7IuMlrM47vPGt1jMkUViseRDM3IN7XqXpbbFGNbqjpNzSjy3iWNbBvoT0poJ3qkQHT8htdSzHjJyUpoaA6b0qgG9pfsil59C");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Content-Length", "103");
        headerMap.put("Content-Type", "application/json;charset=UTF-8");
        headerMap.put("Cookie", ck);
        headerMap.put("Origin", "https://jinbao.pinduoduo.com");
        headerMap.put("Pragma", "no-cache");
        headerMap.put("Priority", "u=1, i");
        headerMap.put("Referer", "https://jinbao.pinduoduo.com/promotion/url-promotion");
        headerMap.put("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        headerMap.put("Sec-Ch-Ua-Mobile", "?0");
        headerMap.put("Sec-Ch-Ua-Platform", "Windows");
        headerMap.put("Sec-Fetch-Dest", "empty");
        headerMap.put("Sec-Fetch-Mode", "cors");
        headerMap.put("Sec-Fetch-Site", "same-origin");
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36");
        headerMap.put("Verifyauthtoken", "Y4yNLCG3U8i1RmG6ZvS9DA9b743e027c890c8b9");
        Headers.Builder builder = new Headers.Builder();
        for (String key : headerMap.keySet()) {
            builder.add(key, headerMap.get(key));
        }
        Headers headers = builder.build();
        RequestBody body = RequestBody.create(JSON, "{\"pid\":\"" + pid + "\",\"sourceUrl\":\"" + goodsUrl + "\"}");
        Request request = new Request.Builder()
                .url("https://jinbao.pinduoduo.com/network/api/promotion/transferUrl")
                .post(body).headers(headers)
                .build();
        //Response response = client.newCall(request).execute();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            // 检查Content-Encoding头
            String contentEncoding = response.header("Content-Encoding");
            ResponseBody responseBody = response.body();
            // 使用GZIPInputStream解压数据
            InputStream gzipStream = new GZIPInputStream(responseBody.byteStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(gzipStream, "UTF-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            printLogMsg("转换链接———：" + result.toString());
            Log.d(MyGlobalVar.TAG, "转换链接1: " + result.toString());
            JSONObject jsonObject = new JSONObject(result.toString());
            String shortUrl = jsonObject.getJSONObject("result").getString("shortUrl");
            return shortUrl;
            /*if ("gzip" .equals(contentEncoding)) {
            } else {
                // 如果不是gzip压缩，直接读取响应体
                System.out.println("转换链接: " + responseBody.string());
                return responseBody.toString();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
            return "0";
        } catch (JSONException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static String transferUrl(String goodsUrl) {
        LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put(":authority", "jinbao.pinduoduo.com");
        headerMap.put(":method", "POST");
        headerMap.put(":path", "/network/api/promotion/transferUrl");
        headerMap.put(":scheme", "https");
        headerMap.put("Accept", "application/json, text/plain, */*");
        headerMap.put("Accept-Encoding", "gzip, deflate, br, zstd");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.9");
        headerMap.put("Anti-Content", "0asAfajyXjHgjgT9Q4JAeAi5A0vNHq-E6WWeUmWcw3IVYz_1HwfkQEVZnyyf1oOrcOyO_E3-t88vAKZYdFwKTmQPZpIxzt5klX6vtjnwToA8hPWdgpouAP6DR9J0QXBeUamT1rYfJA30uxm3cszJjjh0Jum65jx7CsSOnGsZb0R9WN9bhCaC0qQPMiagxZ3UwC8Ll4ioYqiakna2JA2yd8N0ywWb5XVNTPW9jAAbNen6h7DA2dKcCgLdBCUvb0Gc7g7oXzjN0rE9PC92R9xh9E8sSUdb1rZvW9ch9LR2YoVwJ2ZFdWDb6JagLqy2AL9282ox7cGQ0Hp_xrubu6qFRNCTLWY-E1pQ5At2mQzn_EbZUjiUdgPBzkfIsvmJ238pLYaFp3ede_T6f9BTCbT-wdLP9viOF_0O5G3W6p4JwxzjrLbkkjMjgIAtKhxhMBkMhV7tQBME4Ir75DU__fwVTbwt9_dPvPntQJFVhlE8vwzUxKvy_N62p-9onsPXUe8-1ZBXdOhepnsjFC4Rw-Med7fsx2Kr3Bgu-zDXW3imD8IEj7vdKre1T-MyMpnpIE6XjTjPHEv6kJ7yHBNSKKHLmpc7IuMlrM47vPGt1jMkUViseRDM3IN7XqXpbbFGNbqjpNzSjy3iWNbBvoT0poJ3qkQHT8htdSzHjJyUpoaA6b0qgG9pfsil59C");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Content-Length", "103");
        headerMap.put("Content-Type", "application/json;charset=UTF-8");
        headerMap.put("Cookie", HttpUtils.ck);
        headerMap.put("Origin", "https://jinbao.pinduoduo.com");
        headerMap.put("Pragma", "no-cache");
        headerMap.put("Priority", "u=1, i");
        headerMap.put("Referer", "https://jinbao.pinduoduo.com/promotion/url-promotion");
        headerMap.put("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        headerMap.put("Sec-Ch-Ua-Mobile", "?0");
        headerMap.put("Sec-Ch-Ua-Platform", "Windows");
        headerMap.put("Sec-Fetch-Dest", "empty");
        headerMap.put("Sec-Fetch-Mode", "cors");
        headerMap.put("Sec-Fetch-Site", "same-origin");
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36");
        headerMap.put("Verifyauthtoken", "Y4yNLCG3U8i1RmG6ZvS9DA9b743e027c890c8b9");
        Headers.Builder builder = new Headers.Builder();
        for (String key : headerMap.keySet()) {
            builder.add(key, headerMap.get(key));
        }
        Headers headers = builder.build();
        RequestBody body = RequestBody.create(JSON, "{\"pid\":\"" + HttpUtils.pid + "\",\"sourceUrl\":\"" + goodsUrl + "\"}");
        Request request = new Request.Builder()
                .url("https://jinbao.pinduoduo.com/network/api/promotion/transferUrl")
                .post(body).headers(headers)
                .build();
        //Response response = client.newCall(request).execute();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            // 检查Content-Encoding头
            String contentEncoding = response.header("Content-Encoding");
            ResponseBody responseBody = response.body();
            // 使用GZIPInputStream解压数据
            InputStream gzipStream = new GZIPInputStream(responseBody.byteStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(gzipStream, "UTF-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            System.out.println("转换链接1: " + result.toString());
            return result.toString();
            /*if ("gzip" .equals(contentEncoding)) {
            } else {
                // 如果不是gzip压缩，直接读取响应体
                System.out.println("转换链接: " + responseBody.string());
                return responseBody.toString();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
            return "0";
        }
    }
}
