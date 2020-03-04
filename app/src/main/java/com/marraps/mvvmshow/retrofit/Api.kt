package com.marraps.mvvmshow.retrofit

import com.marraps.mvvmshow.numberlist.model.NumbersResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("numbers")
    fun getNumbers(): Call<NumbersResponse>
}