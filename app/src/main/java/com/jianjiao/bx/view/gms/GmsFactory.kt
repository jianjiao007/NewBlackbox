package com.jianjiao.bx.view.gms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jianjiao.bx.data.GmsRepository

class GmsFactory(private val repo: GmsRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GmsViewModel(repo) as T
    }
}
