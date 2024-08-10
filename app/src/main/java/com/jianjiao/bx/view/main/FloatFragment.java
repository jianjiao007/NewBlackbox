package com.jianjiao.bx.view.main;

import static com.jianjiao.bx.Jianjiao.getFloatPermission;
import static com.jianjiao.bx.MyGlobalVar.getGoodsIdForUrl;
import static com.jianjiao.bx.MyGlobalVar.preferences;
import static com.jianjiao.bx.node.AccUtils.isAccessibilityServiceOn;
import static com.jianjiao.bx.node.AccUtils.printLogMsg;
import static com.jianjiao.bx.node.GlobalVariableHolder.context;
import static com.jianjiao.bx.node.GlobalVariableHolder.initDisplay;
import static com.jianjiao.bx.node.GlobalVariableHolder.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.internal.TextWatcherAdapter;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.jianjiao.bx.Jianjiao;
import com.jianjiao.bx.MyGlobalVar;
import com.jianjiao.bx.app.AppManager;
import com.jianjiao.bx.databinding.ActivityMyBinding;
import com.jianjiao.bx.node.FloatingButton;
import com.jianjiao.bx.node.FloatingWindow;
import com.jianjiao.bx.node.utils.FileUtils;
import com.jianjiao.bx.util.InjectionUtil;
import com.jianjiao.bx.view.apps.AppsViewModel;
import com.jianjiao.bx.view.base.LoadingActivity;
import com.vcore.BlackBoxCore;

import java.io.File;
public class FloatFragment extends Fragment {
    public static final String TAG = MyGlobalVar.TAG;
    private static ActivityMyBinding mBinding;
    private static BlackBoxCore core = BlackBoxCore.get();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = ActivityMyBinding.inflate(getLayoutInflater());
        return mBinding.getRoot();//inflater.inflate(R.layout.activity_my, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: "+"重新显示");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mBinding.init.setOnClickListener(v -> init());
        mBinding.userId.setText(preferences.getString("userId", "123456"));
        mBinding.fkWait.setText(preferences.getString("fkWait", "10"));
        mBinding.taskWait.setText(preferences.getString("taskWait", "3"));
        mBinding.costomLink.setText(preferences.getString("costomLink", ""));
        mBinding.ck.setText(preferences.getString("ck", ""));
        mBinding.pid.setText(preferences.getString("pid", ""));
        mBinding.usejinbao.setChecked(preferences.getBoolean("usejinbao", false));
        mBinding.usejinbao.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("usejinbao", isChecked).apply();
        });

        //输入框失焦
        // 使用
        setupOnFocusChangeListener(mBinding.userId, "userId");
        setupOnFocusChangeListener(mBinding.fkWait, "fkWait");
        setupOnFocusChangeListener(mBinding.taskWait, "taskWait");
        setupOnFocusChangeListener(mBinding.costomLink, "costomLink");
        setupOnFocusChangeListener(mBinding.ck, "ck");
        setupOnFocusChangeListener(mBinding.pid, "pid");
        //输入框内容更改
        setTextChange(mBinding.userId, "userId");
        setTextChange(mBinding.fkWait, "fkWait");
        setTextChange(mBinding.taskWait, "taskWait");
        setTextChange(mBinding.costomLink, "costomLink");
        setTextChange(mBinding.pid, "pid");
        setTextChange(mBinding.ck, "ck");

        /*mBinding.userId.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                preferences.edit().putString("userId", mBinding.userId.getText().toString()).apply();
            }
        });
        mBinding.fkWait.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                preferences.edit().putString("fkWait", mBinding.fkWait.getText().toString()).apply();
            }
        });
        mBinding.taskWait.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                preferences.edit().putString("taskWait", mBinding.taskWait.getText().toString()).apply();
            }
        });
        mBinding.costomLink.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                preferences.edit().putString("costomLink", mBinding.costomLink.getText().toString()).apply();
            }
        });
        mBinding.ck.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                preferences.edit().putString("ck", mBinding.ck.getText().toString()).apply();
            }
        });
        mBinding.pid.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                preferences.edit().putString("pid", mBinding.pid.getText().toString()).apply();
            }
        });*/

        /*mBinding.start.setOnClickListener(v -> start());
        mBinding.openVideo.setOnClickListener(v -> openVideo());
        mBinding.resetApp.setOnClickListener(v -> {
            core.clearPackage("com.xunmeng.pinduoduo", 0);
        });*/
    }

    public void showLoading() {
        ((LoadingActivity) requireActivity()).showLoading();
    }

    public void hideLoading() {
        ((LoadingActivity) requireActivity()).hideLoading();
    }

    public void init() {
        //String res = HttpUtils.get("http://43.248.118.77:9995/getTask?userId=");
        //HttpUtils.post("http://43.248.118.77:9995/getTask", "{\"pid\":\"1885064_275817387\",\"sourceUrl\":\"https://mobile.yangkeduo.com/goods.html?goods_id=39138905873\"}");
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "开始转换: ");
                String url = HttpUtils.transferUrl("https://mobile.yangkeduo.com/goods.html?goods_id=111481011714","_nano_fp=XpmxXqgonqPol0TynT_9c0XiHnmKFzy_MhbWR4_S; api_uid=CiHxZma0h4h80gEVzuZHAg==; jrpl=XjcuSQ2gpswjEtjblkDoDnEuBinda150; njrpl=XjcuSQ2gpswjEtjblkDoDnEuBinda150; dilx=W6qBTZrzT7gfBF8pALRtG; DDJB_PASS_ID=53d210d50ed751e528d0dc4d33d14406; DDJB_LOGIN_SCENE=0","1885064_275817387");
                Log.d(TAG, "转换完成: 进宝地址：" + url);
            }
        }).start();*/
        //showLoading();
        //初始化拼多多
        if (!core.isInstalled("com.xunmeng.pinduoduo", 0)) {
            //core.installPackageAsUser("com.xunmeng.pinduoduo", 0);
            File duoduo = FileUtils.getApkFileFromAssets("duoduo.apk");
            core.installPackageAsUser(duoduo, 0);
        }

        //初始化xposed模块
        if (!core.isInstalledXposedModule("com.jianjiao.duoduo")) {
            //core.installXPModule("com.jianjiao.duoduo");
            File xpmodule = FileUtils.getApkFileFromAssets("xpmodule.apk");
            core.installXPModule(xpmodule);
        }
        core.setModuleEnable("com.jianjiao.duoduo", true);
        ViewModelProvider.Factory factory = InjectionUtil.getAppsFactory();
        AppsViewModel viewModel = new ViewModelProvider(this, factory).get(AppsViewModel.class);
        viewModel.getInstalledApps(0);
        ((MainActivity) requireActivity()).scanUser();
        ((MainActivity) requireActivity()).shuaxin();
        core.setXPEnable(true);
        Log.d(TAG, "是否隐藏xp: " + AppManager.getMBlackBoxLoader().hideXposed());
        Log.d(TAG, "是否隐藏xposed: " + AppManager.getMBlackBoxLoader().hideXposed());
        AppManager.getMBlackBoxLoader().invalidHideXposed(true);
        AppManager.getMBlackBoxLoader().invalidHideRoot(true);
        initDisplay();//初始化屏幕信息

        if (!MyGlobalVar.devMode) {
            //getFloatPermission();//初始化悬浮窗权限
            if(!XXPermissions.isGranted(mainActivity, Permission.SYSTEM_ALERT_WINDOW)){
                Toast.makeText(context, "请开启悬浮窗权限", Toast.LENGTH_SHORT).show();
                getFloatPermission();
                return;
            }
            // 打开悬浮窗
            context.startService(new Intent(context, FloatingWindow.class));
            // 打开悬浮窗
            context.startService(new Intent(context, FloatingButton.class));
            //初始化无障碍服务
            if (!isAccessibilityServiceOn()) {
                printLogMsg("请开启无障碍服务", 0);
                Toast.makeText(context, "请开启无障碍服务", Toast.LENGTH_SHORT).show();
                mainActivity.startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                return;
            }
            if (mBinding.usejinbao.isChecked()) {
                if (mBinding.pid.getText().length() < 5) {
                    Toast.makeText(context, "请输入pid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mBinding.ck.getText().length() < 5) {
                    Toast.makeText(context, "请输入令牌", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            else {
                if (mBinding.costomLink.getText().toString().length() < 8) {
                    Toast.makeText(mainActivity, "请输入正确的商品链接:", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (getGoodsIdForUrl(mBinding.costomLink.getText().toString()) == null) {
                    Toast.makeText(context, "自定义链接里面没有goods_id", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }


        Toast.makeText(mainActivity, "初始化完成，可以启动了:", Toast.LENGTH_SHORT).show();
    }

    public void start() {
        //Toast.makeText(MyActivity.this, "启动", Toast.LENGTH_SHORT).show();
        boolean result = core.launchApk("com.xunmeng.pinduoduo", 0);
        if (!result) {
            Toast.makeText(mainActivity, "启动失败", Toast.LENGTH_SHORT).show();
        }
    }


    private void setupOnFocusChangeListener(EditText view, String key) {
        view.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                preferences.edit().putString(key, editText.getText().toString()).apply();
            }
        });
    }

    private void setTextChange(EditText view, String key) {
        view.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                // 正确的做法是直接使用传入的view，而不是尝试转换s
                Log.d(TAG, "TextChanged: " + key + "___" + s.toString());
                preferences.edit().putString(key, s.toString()).apply();
            }
        });
    }
}