package com.jianjiao.bx.app

import android.content.Context
import android.content.SharedPreferences
import com.jianjiao.bx.view.main.BlackBoxLoader

object AppManager {
    @JvmStatic
    val mBlackBoxLoader by lazy {
        BlackBoxLoader()
    }

    @JvmStatic
    val mRemarkSharedPreferences: SharedPreferences by lazy {
        com.jianjiao.bx.app.App.Companion.getContext().getSharedPreferences("UserRemark", Context.MODE_PRIVATE)
    }

    fun doAttachBaseContext(context: Context) {
        try {
            com.jianjiao.bx.app.AppManager.mBlackBoxLoader.attachBaseContext(context)
            com.jianjiao.bx.app.AppManager.mBlackBoxLoader.addLifecycleCallback()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun doOnCreate() {
        com.jianjiao.bx.app.AppManager.mBlackBoxLoader.doOnCreate()
        com.jianjiao.bx.app.AppManager.initThirdService()
    }

    private fun initThirdService() { }
}
