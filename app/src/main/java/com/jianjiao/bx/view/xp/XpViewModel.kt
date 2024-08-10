package com.jianjiao.bx.view.xp

import androidx.lifecycle.MutableLiveData
import com.jianjiao.bx.bean.XpModuleInfo
import com.jianjiao.bx.data.XpRepository
import com.jianjiao.bx.view.base.BaseViewModel

class XpViewModel(private val repo: XpRepository) : BaseViewModel() {
    val appsLiveData = MutableLiveData<List<com.jianjiao.bx.bean.XpModuleInfo>>()
    val resultLiveData = MutableLiveData<String>()

    fun getInstalledModule() {
        launchOnUI {
            repo.getInstallModules(appsLiveData)
        }
    }

    fun installModule(source:String) {
        launchOnUI {
            repo.installModule(source, resultLiveData)
        }
    }

    fun unInstallModule(packageName: String) {
        launchOnUI {
            repo.unInstallModule(packageName, resultLiveData)
        }
    }
}
