package com.marraps.mvvmshow.numberlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marraps.mvvmshow.numberlist.model.NumberListRepository
import com.marraps.mvvmshow.numberlist.model.NumbersResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NumberListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: NumberListRepository

    lateinit var viewModel: NumberListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = NumberListViewModel(repository)
    }

    @Test
    fun getNumbers_successResponse(){
        val response: NumbersResponse = mockk()
        val numberList = listOf(1, 2, 3, 4)

        every { repository.getNumbers(viewModel) } answers {viewModel.onSuccess(response)}
        every { response.numbers } returns numberList

        viewModel.getNumbers()

        assertEquals(NumberListViewModel.Command.HideLoading, viewModel.command.value)
        assertEquals(numberList, viewModel.numberList.value)
    }

    @Test
    fun getNumbers_errorResponse(){
        val error = Exception()

        every { repository.getNumbers(viewModel) } answers {viewModel.onError(error)}

        viewModel.getNumbers()

        assertEquals(NumberListViewModel.Command.HideLoading, viewModel.command.value)
        assertEquals(error, viewModel.numberError.value)
    }
}