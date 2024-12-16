package com.example.data.mapper

import com.example.data.remote.dto.CountryInfoResponse
import com.example.data.remote.dto.CurrentWeatherResponse
import com.example.data.repository.model.CountryInfo
import com.example.data.repository.model.CurrentWeather



fun CurrentWeatherResponse.toCurrentWeather() =
    CurrentWeather(
        cityName = location.name,
        temperatureByKelvin = current.temp_c,
        humidity = current.humidity,
        uv = current.uv,
        feelsLikeTemperatureByKelvin = current.feelslike_c,
        weatherCondition = current.condition.text,
        weatherConditionIcon = current.condition.icon
    )


fun CountryInfoResponse.toCountryInfo() =
    CountryInfo(
        id = id ,
        name = name ,
        region = region ,
        country = country ,
        lat = lat,
        lon = lon,
        url = url
    )