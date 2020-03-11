package com.marraps.mvvmshow.features.numberlist.model

import com.marraps.mvvmshow.coroutines.AppContextProvider
import com.marraps.mvvmshow.retrofit.Api
import kotlinx.coroutines.withContext

class NumberListRepository(private val service: Api) {

    suspend fun getNumbers(): NumbersResponse = withContext(AppContextProvider.io) {
        service.getNumbers()
    }
}