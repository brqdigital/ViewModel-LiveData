package com.marraps.mvvmshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marraps.mvvmshow.numberlist.model.NumberListRepository
import com.marraps.mvvmshow.numberlist.viewmodel.NumberListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(NumberListViewModel::class.java) ->
                    NumberListViewModel(NumberListRepository())

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}