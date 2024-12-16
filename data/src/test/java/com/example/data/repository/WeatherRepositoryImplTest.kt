package com.example.data.repository

import com.example.common.Resources
import com.example.data.local.WeatherSharedPreferences
import com.example.data.mapper.toCountryInfo
import com.example.data.mapper.toCurrentWeather
import com.example.data.remote.WeatherApi
import com.example.data.util.countryInfoResponseFake
import com.example.data.util.currentWeatherResponseFake
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class WeatherRepositoryImplTest {

    private lateinit var weatherApi : WeatherApi
    private lateinit var weatherSharedPreferences : WeatherSharedPreferences
    private lateinit var repository : WeatherRepositoryImpl

    @Before
    fun setUp(){
        weatherApi = mock()
        weatherSharedPreferences = mock()
        repository = WeatherRepositoryImpl(weatherApi, weatherSharedPreferences)
    }

    @Test
    fun `getCountries returns success`() = runTest {
         `when`(weatherApi.getCountries(query = "Sharjah"))
             .thenReturn(countryInfoResponseFake)

        val expected = Resources.Success(countryInfoResponseFake.map { it.toCountryInfo() })
        val actual = repository.getCountries("Sharjah").last()

        assertEquals(expected.data ,  actual.data)
    }

    @Test
    fun `getCountries returns error when Exception is thrown`() = runTest {
        `when`(weatherApi.getCountries(query = "Sharjah"))
            .thenThrow(RuntimeException("Check your internet connection"))

        var message: String? = null

        try {
            val response = repository.getCountries("Sharjah").last()
        }catch (ex : Exception){
            message = ex.message
        }

        assertEquals(message , "Check your internet connection")
    }

    @Test
    fun `getCurrentWeather return success`() = runTest {
        `when`(weatherApi.getCurrentWeather(query = "Sharjah"))
            .thenReturn(currentWeatherResponseFake)

        `when`(weatherSharedPreferences.getWeatherCurrent())
            .thenReturn(null)

        val expected = Resources.Success(currentWeatherResponseFake.toCurrentWeather())
        val actual = repository.getCurrentWeather("Sharjah").last()

        assertEquals(expected.data , actual.data)
    }

    @Test
    fun `getCurrentWeather return error when Exception is thrown`() = runTest {

        `when`(weatherApi.getCurrentWeather(query = "Sharjah"))
            .thenThrow(RuntimeException("Check your internet connection"))

        `when`(weatherSharedPreferences.getWeatherCurrent())
            .thenReturn(null)

        var message: String? = null

        try {
            val response = repository.getCurrentWeather("Sharjah").last()
        }catch (ex : Exception){
            message = ex.message

            assertEquals(message , "Check your internet connection")
        }
    }

}