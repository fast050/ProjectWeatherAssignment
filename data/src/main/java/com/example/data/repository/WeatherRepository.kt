package com.example.data.repository

import com.example.common.Resources
import com.example.common.model.CountryInfo
import com.example.common.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCountries(query: String): Flow<Resources<List<CountryInfo>>>
    fun getCurrentWeather(query: String):  Flow<Resources<CurrentWeather>>
    fun saveLocalWeather(currentWeather: CurrentWeather)
    fun getLocalWeather(): CurrentWeather?
}
