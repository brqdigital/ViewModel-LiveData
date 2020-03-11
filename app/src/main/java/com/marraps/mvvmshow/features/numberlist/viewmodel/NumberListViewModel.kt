package com.marraps.mvvmshow.features.numberlist.viewmodel

import androidx.lifecycle.MutableLiveData
import com.marraps.mvvmshow.application.BaseViewModel
import com.marraps.mvvmshow.features.numberlist.model.NumberListRepository
import kotlinx.coroutines.launch

class NumberListViewModel(private val repository: NumberListRepository) : BaseViewModel() {

    sealed class Command {
        object ShowLoading : Command()
        object HideLoading : Command()
    }

    val command = MutableLiveData<Command>()
    val numberList = MutableLiveData<List<Int>>()
    val numberError = MutableLiveData<Throwable>()

    fun getNumbers() {
        command.value = Command.ShowLoading

        launch {
            try {
                val response = repository.getNumbers()
                numberList.postValue(response.numbers)

            } catch (error: Exception) {
                numberError.postValue(error)
            }

            command.postValue(Command.HideLoading)
        }
    }
}