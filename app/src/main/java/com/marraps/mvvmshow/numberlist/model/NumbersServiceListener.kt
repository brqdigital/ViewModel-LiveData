package com.marraps.mvvmshow.numberlist.model

interface NumbersServiceListener {

    fun onSuccess(response: NumbersResponse)
    fun onError(error: Throwable)
}