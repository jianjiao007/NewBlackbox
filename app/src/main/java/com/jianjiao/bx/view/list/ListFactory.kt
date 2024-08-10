package com.jianjiao.bx.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jianjiao.bx.data.AppsRepository

@Suppress("UNCHECKED_CAST")
class ListFactory(private val appsRepository: AppsRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(appsRepository) as T
    }
}
