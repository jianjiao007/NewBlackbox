package com.jianjiao.bx.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private lateinit var mContext: Context

        @JvmStatic
        fun getContext(): Context {
            return com.jianjiao.bx.app.App.Companion.mContext
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        com.jianjiao.bx.app.App.Companion.mContext = base!!
        com.jianjiao.bx.app.AppManager.doAttachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        com.jianjiao.bx.app.AppManager.doOnCreate()
    }
}
