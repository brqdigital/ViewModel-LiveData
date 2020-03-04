package com.marraps.mvvmshow.numberlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marraps.mvvmshow.numberlist.model.NumberListRepository
import com.marraps.mvvmshow.numberlist.model.NumbersResponse
import com.marraps.mvvmshow.numberlist.model.NumbersServiceListener

class NumberListViewModel(private val repository: NumberListRepository): ViewModel(), NumbersServiceListener {

    sealed class Command{
        object ShowLoading: Command()
        object HideLoading: Command()
    }

    val command = MutableLiveData<Command>()
    val numberList = MutableLiveData<List<Int>>()
    val numberError = MutableLiveData<Throwable>()

    fun getNumbers(){
        command.value = Command.ShowLoading
        repository.getNumbers(this)
    }

    override fun onSuccess(response: NumbersResponse) {
        command.value = Command.HideLoading
        numberList.value = response.numbers
    }

    override fun onError(error: Throwable) {
        command.value = Command.HideLoading
        numberError.value = error
    }
}