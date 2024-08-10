package com.jianjiao.bx.data

import android.content.pm.ApplicationInfo
import androidx.lifecycle.MutableLiveData
import com.vcore.BlackBoxCore
import com.vcore.entity.location.BLocation
import com.vcore.fake.frameworks.BLocationManager
import com.vcore.utils.Slog
import com.jianjiao.bx.bean.FakeLocationBean

class FakeLocationRepository {
    private val TAG: String = "FakeLocationRepository"

    fun setPattern(userId: Int, pkg: String, pattern: Int) {
        BLocationManager.get().setPattern(userId, pkg, pattern)
    }

    private fun getPattern(userId: Int, pkg: String): Int {
        return BLocationManager.get().getPattern(userId, pkg)
    }

    private fun getLocation(userId: Int, pkg: String): BLocation? {
        return BLocationManager.get().getLocation(userId, pkg)
    }

    fun setLocation(userId: Int, pkg: String, location: BLocation) {
        BLocationManager.get().setLocation(userId, pkg, location)
    }

    fun getInstalledAppList(userID: Int, appsFakeLiveData: MutableLiveData<List<com.jianjiao.bx.bean.FakeLocationBean>>) {
        val installedList = mutableListOf<com.jianjiao.bx.bean.FakeLocationBean>()
        val installedApplications: List<ApplicationInfo> = BlackBoxCore.get().getInstalledApplications(0, userID)
        // List<ApplicationInfo> -> List<FakeLocationBean>
        for (installedApplication in installedApplications) {
            /*val file = File(installedApplication.sourceDir)
            if ((installedApplication.flags and ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue
            }

            if (!AbiUtils.isSupport(file)) {
                continue
            }

            val isXpModule = BlackBoxCore.get().isXposedModule(file)*/
            val info = com.jianjiao.bx.bean.FakeLocationBean(
                userID,
                installedApplication.loadLabel(BlackBoxCore.getPackageManager()).toString(),
                installedApplication.loadIcon(BlackBoxCore.getPackageManager()),
                installedApplication.packageName,
                getPattern(userID, installedApplication.packageName),
                getLocation(userID, installedApplication.packageName)
            )

            installedList.add(info)
        }

        Slog.d(TAG, installedList.joinToString(","))
        appsFakeLiveData.postValue(installedList)
    }
}
