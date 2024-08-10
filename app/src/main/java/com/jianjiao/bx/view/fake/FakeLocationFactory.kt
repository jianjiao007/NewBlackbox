package com.jianjiao.bx.view.fake

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jianjiao.bx.data.FakeLocationRepository

class FakeLocationFactory(private val repo: FakeLocationRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FakeLocationViewModel(repo) as T
    }
}
