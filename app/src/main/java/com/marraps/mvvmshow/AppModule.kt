package com.marraps.mvvmshow

import com.marraps.mvvmshow.numberlist.model.NumberListRepository
import com.marraps.mvvmshow.numberlist.viewmodel.NumberListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { NumberListRepository() }

    viewModel {NumberListViewModel(get())}
}