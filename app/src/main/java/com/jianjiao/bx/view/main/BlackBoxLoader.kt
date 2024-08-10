package com.jianjiao.bx.view.main

import android.app.Application
import android.content.Context
import com.vcore.BlackBoxCore
import com.vcore.app.BActivityThread.getUserId
import com.vcore.app.configuration.AppLifecycleCallback
import com.vcore.app.configuration.ClientConfiguration
import com.vcore.utils.Slog
import com.jianjiao.bx.app.App
import com.jianjiao.bx.cache.AppSharedPreferenceDelegate
import java.io.File

class BlackBoxLoader {
    private var mHideRoot by com.jianjiao.bx.cache.AppSharedPreferenceDelegate(
        com.jianjiao.bx.app.App.getContext(),
        false
    )
    private var mHideXposed by com.jianjiao.bx.cache.AppSharedPreferenceDelegate(
        com.jianjiao.bx.app.App.getContext(),
        false
    )
    private var mDaemonEnable by com.jianjiao.bx.cache.AppSharedPreferenceDelegate(
        com.jianjiao.bx.app.App.getContext(),
        false
    )
    private var mShowShortcutPermissionDialog by com.jianjiao.bx.cache.AppSharedPreferenceDelegate(
        com.jianjiao.bx.app.App.getContext(),
        true
    )

    fun hideRoot(): Boolean {
        return mHideRoot
    }

    fun invalidHideRoot(hideRoot: Boolean) {
        this.mHideRoot = hideRoot
    }

    fun hideXposed(): Boolean {
        return mHideXposed
    }

    fun invalidHideXposed(hideXposed: Boolean) {
        this.mHideXposed = hideXposed
    }

    fun daemonEnable(): Boolean {
        return mDaemonEnable
    }

    fun invalidDaemonEnable(enable: Boolean) {
        this.mDaemonEnable = enable
    }

    fun showShortcutPermissionDialog(): Boolean {
        return mShowShortcutPermissionDialog
    }

    fun addLifecycleCallback() {
        BlackBoxCore.get().addAppLifecycleCallback(object : AppLifecycleCallback() {
            override fun beforeCreateApplication(packageName: String?, processName: String?, context: Context?, userId: Int) {
                Slog.d(TAG, "beforeCreateApplication: pkg $packageName, processName $processName, userID:${getUserId()}")
            }

            override fun beforeApplicationOnCreate(packageName: String?, processName: String?, application: Application?, userId: Int) {
                Slog.d(TAG, "beforeApplicationOnCreate: pkg $packageName, processName $processName")
            }

            override fun afterApplicationOnCreate(packageName: String?, processName: String?, application: Application?, userId: Int) {
                Slog.d(TAG, "afterApplicationOnCreate: pkg $packageName, processName $processName")
            }
        })
    }

    fun attachBaseContext(context: Context) {
        BlackBoxCore.get().doAttachBaseContext(context, object : ClientConfiguration() {
            override fun getHostPackageName(): String {
                return context.packageName
            }

            override fun isHideRoot(): Boolean {
                return mHideRoot
            }

            override fun isHideXposed(): Boolean {
                return mHideXposed
            }

            override fun isEnableDaemonService(): Boolean {
                return mDaemonEnable
            }

            override fun requestInstallPackage(file: File?, userId: Int): Boolean {
                return false
            }
        })
    }

    fun doOnCreate() {
        BlackBoxCore.get().doCreate()
    }

    companion object {
        val TAG: String = BlackBoxLoader::class.java.simpleName
    }
}
