package com.example.projectweatherassignment.ui.search

import com.example.common.model.CountryInfo
import com.example.common.model.CurrentWeather

data class SearchState (
    val isLoading: Boolean = false,
    val isEmpty: Boolean = true,
    val countries: List<CountryInfo> = emptyList(),
    val currentWeatherList : List<CurrentWeather>?= emptyList(),
    val searchQuery: String = "",
    val errorMessage: String? = null
)