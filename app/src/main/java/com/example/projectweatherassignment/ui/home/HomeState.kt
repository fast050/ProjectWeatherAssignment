package com.example.projectweatherassignment.ui.home
import com.example.common.model.CurrentWeather

data class HomeState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = true,
    val currentWeather: CurrentWeather? = null,
    val errorMessage: String? = null
)
