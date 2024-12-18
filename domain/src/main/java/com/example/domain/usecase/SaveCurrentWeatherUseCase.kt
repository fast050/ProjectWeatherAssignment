package com.example.domain.usecase

import com.example.common.model.CurrentWeather
import com.example.data.repository.WeatherRepository
import javax.inject.Inject

class SaveCurrentWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke(currentWeather: CurrentWeather) {
        repository.saveLocalWeather(currentWeather)
    }
}