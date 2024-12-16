package com.example.domain.usecase

import com.example.common.Resources
import com.example.data.repository.WeatherRepository
import com.example.data.repository.model.CurrentWeather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(private val repository: WeatherRepository){
    operator fun invoke(query: String) : Flow<Resources<CurrentWeather>> =
           repository.getCurrentWeather(query)
}