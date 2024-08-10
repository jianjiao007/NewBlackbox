package com.jianjiao.bx.view.gms

import androidx.lifecycle.MutableLiveData
import com.jianjiao.bx.bean.GmsBean
import com.jianjiao.bx.bean.GmsInstallBean
import com.jianjiao.bx.data.GmsRepository
import com.jianjiao.bx.view.base.BaseViewModel

class GmsViewModel(private val mRepo: GmsRepository) : BaseViewModel() {
    val mInstalledLiveData = MutableLiveData<List<com.jianjiao.bx.bean.GmsBean>>()
    val mUpdateInstalledLiveData = MutableLiveData<com.jianjiao.bx.bean.GmsInstallBean>()

    fun getInstalledUser() {
        launchOnUI {
            mRepo.getGmsInstalledList(mInstalledLiveData)
        }
    }

    fun installGms(userID: Int) {
        launchOnUI {
            mRepo.installGms(userID, mUpdateInstalledLiveData)
        }
    }

    fun uninstallGms(userID: Int) {
        launchOnUI {
            mRepo.uninstallGms(userID, mUpdateInstalledLiveData)
        }
    }
}
