package com.marraps.mvvmshow.retrofit

import com.marraps.mvvmshow.features.numberlist.model.NumbersResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("numbers")
    suspend fun getNumbers(): NumbersResponse
}