package com.example.domain.util

import com.example.common.model.CountryInfo
import com.example.common.model.CurrentWeather



val countryInfoFakeList = listOf<CountryInfo>(
    CountryInfo(
        id = 780,
        name = "Sharjah",
        region = "Sharjah",
        country = "United Arab Emirates",
        lat = 25.36,
        lon = 55.39,
        url = "sharjah-sharjah-united-arab-emirates"
    ),
    CountryInfo(
        id = 500,
        name = "Dubai",
        region = "Dubai",
        country = "United Arab Emirates",
        lat = 20.11,
        lon = 50.22,
        url = "Dubai-Dubai-united-arab-emirates"
    )
)



val currentWeatherFake = CurrentWeather(
    cityName = "Dubai",
    temperatureByKelvin = 20.0,
    humidity =  25,
    uv = 2,
    feelsLikeTemperatureByKelvin =  20.55,
    weatherCondition = "Sunny",
    weatherConditionIcon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
)