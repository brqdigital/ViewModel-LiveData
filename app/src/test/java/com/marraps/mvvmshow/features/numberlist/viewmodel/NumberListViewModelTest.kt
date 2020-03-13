package com.marraps.mvvmshow.features.numberlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marraps.mvvmshow.coroutines.AppContextProvider
import com.marraps.mvvmshow.coroutines.TestContextProvider
import com.marraps.mvvmshow.features.numberlist.model.NumberListRepository
import com.marraps.mvvmshow.features.numberlist.model.NumbersResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
    private lateinit var repository: NumberListRepository

    private lateinit var viewModel: NumberListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        AppContextProvider.coroutinesContextProviderDelegate = TestContextProvider()
        viewModel = NumberListViewModel(repository)
    }

    @Test
    fun getNumbers_successResponse() {
        val numbersResponse: NumbersResponse = mockk()
        val numberList = listOf(1, 2, 3, 4, 5)

        coEvery { repository.getNumbers() } returns numbersResponse
        every { numbersResponse.numbers } returns numberList

        viewModel.getNumbers()

        assertEquals(numberList, viewModel.numberList.value)
        assertEquals(NumberListViewModel.Command.HideLoading, viewModel.command.value)
    }

    @Test
    fun getNumbers_errorResponse() {
        val error = Exception()

        coEvery { repository.getNumbers() } throws error

        viewModel.getNumbers()

        assertEquals(error, viewModel.numberError.value)
        assertEquals(NumberListViewModel.Command.HideLoading, viewModel.command.value)
    }
}