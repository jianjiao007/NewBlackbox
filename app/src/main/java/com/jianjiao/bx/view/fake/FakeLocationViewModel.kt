package com.jianjiao.bx.view.fake

import androidx.lifecycle.MutableLiveData
import com.vcore.entity.location.BLocation
import com.jianjiao.bx.bean.FakeLocationBean
import com.jianjiao.bx.data.FakeLocationRepository
import com.jianjiao.bx.view.base.BaseViewModel

class FakeLocationViewModel(private val mRepo: FakeLocationRepository) : BaseViewModel() {
    val appsLiveData = MutableLiveData<List<com.jianjiao.bx.bean.FakeLocationBean>>()

    fun getInstallAppList(userID: Int) {
        launchOnUI {
            mRepo.getInstalledAppList(userID, appsLiveData)
        }
    }

    fun setPattern(userId: Int, pkg: String, pattern: Int) {
        launchOnUI {
            mRepo.setPattern(userId, pkg, pattern)
        }
    }

    fun setLocation(userId: Int, pkg: String, location: BLocation) {
        launchOnUI {
            mRepo.setLocation(userId, pkg, location)
        }
    }
}
