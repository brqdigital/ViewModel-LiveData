package com.marraps.mvvmshow.koin

import com.marraps.mvvmshow.features.numberlist.model.NumberListRepository
import com.marraps.mvvmshow.features.numberlist.viewmodel.NumberListViewModel
import com.marraps.mvvmshow.retrofit.RetrofitService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RetrofitService.getService() }

    single { NumberListRepository(get()) }

    viewModel { NumberListViewModel(get()) }
}