package com.example.data.repository.model

data class  CurrentWeather(
    val cityName: String,
    val temperatureByKelvin: Double,
    val humidity :Int,
    val uv :Int,
    val feelsLikeTemperatureByKelvin: Double,
    val weatherCondition: String,
    val weatherConditionIcon: String
)