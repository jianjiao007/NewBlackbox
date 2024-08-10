package com.jianjiao.bx.util

import androidx.annotation.StringRes
import com.jianjiao.bx.app.App

object ResUtil {
    fun getString(@StringRes id: Int, vararg arg: String): String {
        if (arg.isEmpty()) {
            return com.jianjiao.bx.app.App.getContext().getString(id)
        }
        return com.jianjiao.bx.app.App.getContext().getString(id, *arg)
    }
}
