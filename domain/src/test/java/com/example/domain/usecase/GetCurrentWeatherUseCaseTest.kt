package com.example.domain.usecase

import com.example.common.Resources
import com.example.data.repository.WeatherRepository
import com.example.domain.util.countryInfoFakeList
import com.example.domain.util.currentWeatherFake
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetCurrentWeatherUseCaseTest {

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetCurrentWeatherUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetCurrentWeatherUseCase(repository)
    }

    @Test
    fun `invoke calls getCurrentWeather from repository and success`() = runTest {

        `when`(repository.getCurrentWeather("Sharjah"))
            .thenReturn(flow { emit(Resources.Success(currentWeatherFake)) })

        val expected = Resources.Success(currentWeatherFake).data
        val actual = useCase("Sharjah").last().data

        assertEquals(expected , actual)
    }

    @Test
    fun `invoke calls getCurrentWeather from repository and error`() = runTest {

        `when`(repository.getCurrentWeather("Sharjah"))
            .thenReturn(
                flow { emit(Resources.Error(null , "Check your internet connection"))}
            )

        val expected = Resources.Error(null , "Check your internet connection").message
        val actual = useCase("Sharjah").last().message

        assertEquals(expected , actual)
    }


}