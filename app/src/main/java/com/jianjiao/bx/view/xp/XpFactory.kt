package com.jianjiao.bx.view.xp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jianjiao.bx.data.XpRepository

@Suppress("UNCHECKED_CAST")
class XpFactory(private val repo: XpRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return XpViewModel(repo) as T
    }
}
