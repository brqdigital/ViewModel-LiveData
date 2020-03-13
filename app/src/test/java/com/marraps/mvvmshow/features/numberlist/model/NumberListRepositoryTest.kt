package com.marraps.mvvmshow.features.numberlist.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marraps.mvvmshow.coroutines.AppContextProvider
import com.marraps.mvvmshow.coroutines.TestContextProvider
import com.marraps.mvvmshow.retrofit.Api
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NumberListRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var service: Api

    private lateinit var repository: NumberListRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        AppContextProvider.coroutinesContextProviderDelegate = TestContextProvider()
        repository = NumberListRepository(service)
    }

    @Test
    fun getNumbers() = runBlocking {
        val numbersResponse: NumbersResponse = mockk()
        coEvery { service.getNumbers() } returns numbersResponse

        val ret = repository.getNumbers()

        assertEquals(numbersResponse, ret)
    }
}