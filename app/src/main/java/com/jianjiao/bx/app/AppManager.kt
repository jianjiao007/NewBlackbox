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
            mBlackBoxLoader.attachBaseContext(context)
            mBlackBoxLoader.addLifecycleCallback()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun doOnCreate() {
        mBlackBoxLoader.doOnCreate()
        initThirdService()
    }

    private fun initThirdService() { }
}
