package com.jianjiao.bx.data

import androidx.lifecycle.MutableLiveData
import com.jianjiao.bx.R
import com.jianjiao.bx.util.ResUtil.getString
import com.vcore.BlackBoxCore

class GmsRepository {
    fun getGmsInstalledList(mInstalledLiveData: MutableLiveData<List<com.jianjiao.bx.bean.GmsBean>>) {
        val userList = arrayListOf<com.jianjiao.bx.bean.GmsBean>()

        BlackBoxCore.get().users.forEach {
            val userId = it.id
            val userName = com.jianjiao.bx.app.AppManager.mRemarkSharedPreferences.getString("Remark$userId", "User $userId") ?: ""
            val isInstalled = BlackBoxCore.get().isInstallGms(userId)
            val bean = com.jianjiao.bx.bean.GmsBean(userId, userName, isInstalled)
            userList.add(bean)
        }
        mInstalledLiveData.postValue(userList)
    }

    fun installGms(userID: Int, mUpdateInstalledLiveData: MutableLiveData<com.jianjiao.bx.bean.GmsInstallBean>) {
        val installResult = BlackBoxCore.get().installGms(userID)
        val result = if (installResult.success) {
            getString(R.string.install_success)
        } else {
            getString(R.string.install_fail, installResult.msg)
        }

        val bean = com.jianjiao.bx.bean.GmsInstallBean(userID, installResult.success, result)
        mUpdateInstalledLiveData.postValue(bean)
    }

    fun uninstallGms(userID: Int, mUpdateInstalledLiveData: MutableLiveData<com.jianjiao.bx.bean.GmsInstallBean>) {
        var isSuccess = false
        if (BlackBoxCore.get().isInstallGms(userID)) {
            isSuccess = BlackBoxCore.get().uninstallGms(userID)
        }

        val result = if (isSuccess) {
            getString(R.string.uninstall_success)
        } else {
            getString(R.string.uninstall_fail)
        }

        val bean = com.jianjiao.bx.bean.GmsInstallBean(userID, isSuccess, result)
        mUpdateInstalledLiveData.postValue(bean)
    }
}
