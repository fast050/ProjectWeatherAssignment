package com.example.data.util

import com.example.data.remote.dto.CountryInfoResponse
import com.example.data.remote.dto.CurrentWeatherResponse
import com.example.data.repository.model.CountryInfo
import kotlin.Double


val countryInfoResponseFake = listOf<CountryInfoResponse>(
    CountryInfoResponse(
        id = 780,
        name = "Sharjah",
        region = "Sharjah",
        country = "United Arab Emirates",
        lat = 25.36,
        lon = 55.39,
        url = "sharjah-sharjah-united-arab-emirates"
    ),
    CountryInfoResponse(
        id = 500,
        name = "Dubai",
        region = "Dubai",
        country = "United Arab Emirates",
        lat = 20.11,
        lon = 50.22,
        url = "Dubai-Dubai-united-arab-emirates"
    )
)

val currentWeatherResponseFake = CurrentWeatherResponse(
    current = CurrentWeatherResponse.Current(
        temp_c = 20.0,
        temp_f = 68.0,
        condition = CurrentWeatherResponse.Current.Condition(
            text = "Sunny",
            icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
        ),
        humidity =  25,
        uv = 2,
        feelslike_c=  20.55,
        feelslike_f = 54.54,
    ),
    location = CurrentWeatherResponse.Location(
        name = "Sharjah"
    )
)