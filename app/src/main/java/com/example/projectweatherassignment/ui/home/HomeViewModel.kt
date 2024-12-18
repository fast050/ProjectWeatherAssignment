package com.example.projectweatherassignment.ui.home

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetLocalCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetLocalCurrentWeatherUseCase,
) : ViewModel() {

    var state by mutableStateOf(HomeState())
     private set

    /**
     *  if we call it with (city == null) the result is null
     *  then the data local storage is empty (equal to null)
     */

    fun getCurrentWeather() = viewModelScope.launch {
        val result = getCurrentWeatherUseCase()

        state = if (result == null) {
            state.copy(
                currentWeather = result,
                isLoading = false,
                isEmpty = true
            )
        } else
            state.copy(
                currentWeather = result,
                isLoading = false,
                isEmpty = false
            )

    }

    init {
        getCurrentWeather()
    }

}