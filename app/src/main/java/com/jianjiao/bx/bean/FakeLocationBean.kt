package com.jianjiao.bx.bean

import android.graphics.drawable.Drawable
import com.vcore.entity.location.BLocation

data class FakeLocationBean(
    val userID: Int,
    val name: String,
    val icon: Drawable,
    val packageName: String,
    var fakeLocationPattern: Int,
    var fakeLocation: BLocation?
)
