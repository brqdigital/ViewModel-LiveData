package com.marraps.mvvmshow.numberlist.model

import com.marraps.mvvmshow.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NumberListRepository {

    fun getNumbers(listener: NumbersServiceListener) {

        val call = RetrofitService.getService().getNumbers()
        call.enqueue(object : Callback<NumbersResponse> {

            override fun onResponse(
                call: Call<NumbersResponse>,
                response: Response<NumbersResponse>
            ) {
                response.body()?.let {
                    listener.onSuccess(it)

                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<NumbersResponse>, t: Throwable) {
                listener.onError(t)
            }
        })
    }
}