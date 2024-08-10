package com.jianjiao.bx.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.jianjiao.bx.app.App
import com.jianjiao.bx.util.ResUtil.getString

object ToastEx {
    private var toastImpl:Toast? = null

    fun Context.toast(msg:String) {
        toastImpl?.cancel()
        toastImpl = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        toastImpl?.show()
    }

    fun toast(msg: String) {
        com.jianjiao.bx.app.App.getContext().toast(msg)
    }

    fun toast(@StringRes msgID:Int) {
        toast(getString(msgID))
    }
}
