package com.example.projectweatherassignment.home

import com.example.domain.usecase.GetLocalCurrentWeatherUseCase
import com.example.projectweatherassignment.util.currentWeatherFake
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class HomeViewModelTest {

    private lateinit var getCurrentWeatherUseCase : GetLocalCurrentWeatherUseCase


    @Before
    fun setUp(){
        getCurrentWeatherUseCase = mock()
    }

    @Test
    fun `test getCurrentWeather is return saved current weather not null`(){

        `when`(getCurrentWeatherUseCase())
            .thenReturn(currentWeatherFake)

        val expected = currentWeatherFake
        val actual = getCurrentWeatherUseCase()

        assertEquals(expected , actual)
    }

    @Test
    fun `test getCurrentWeather is return saved current weather null`(){
        `when`(getCurrentWeatherUseCase())
            .thenReturn(null)

        val expected = null
        val actual = getCurrentWeatherUseCase()

        assertEquals(expected , actual)
    }

}