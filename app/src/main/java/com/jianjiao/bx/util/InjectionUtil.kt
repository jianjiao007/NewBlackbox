package com.jianjiao.bx.util

import com.jianjiao.bx.data.AppsRepository
import com.jianjiao.bx.data.FakeLocationRepository
import com.jianjiao.bx.data.GmsRepository
import com.jianjiao.bx.data.XpRepository
import com.jianjiao.bx.view.apps.AppsFactory
import com.jianjiao.bx.view.fake.FakeLocationFactory
import com.jianjiao.bx.view.gms.GmsFactory
import com.jianjiao.bx.view.list.ListFactory
import com.jianjiao.bx.view.xp.XpFactory

object InjectionUtil {
    private val appsRepository = AppsRepository()
    private val xpRepository = XpRepository()
    private val gmsRepository = GmsRepository()
    private val fakeLocationRepository = FakeLocationRepository()

    @JvmStatic
    fun getAppsFactory() : AppsFactory {
        return AppsFactory(appsRepository)
    }

    fun getListFactory(): ListFactory {
        return ListFactory(appsRepository)
    }

    fun getXpFactory(): XpFactory {
        return XpFactory(xpRepository)
    }

    fun getGmsFactory(): GmsFactory {
        return GmsFactory(gmsRepository)
    }

    fun getFakeLocationFactory(): FakeLocationFactory {
        return FakeLocationFactory(fakeLocationRepository)
    }
}
