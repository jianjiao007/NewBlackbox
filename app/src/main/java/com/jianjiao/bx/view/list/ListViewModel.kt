package com.jianjiao.bx.view.list

import androidx.lifecycle.MutableLiveData
import com.jianjiao.bx.bean.InstalledAppBean
import com.jianjiao.bx.data.AppsRepository
import com.jianjiao.bx.view.base.BaseViewModel

class ListViewModel(private val repo: AppsRepository) : BaseViewModel() {
    val appsLiveData = MutableLiveData<List<com.jianjiao.bx.bean.InstalledAppBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun previewInstalledList() {
        launchOnUI{
            repo.previewInstallList()
        }
    }

    fun getInstallAppList(userID: Int) {
        launchOnUI {
            repo.getInstalledAppList(userID, loadingLiveData, appsLiveData)
        }
    }

    fun getInstalledModules() {
        launchOnUI {
            repo.getInstalledModuleList(loadingLiveData, appsLiveData)
        }
    }
}
