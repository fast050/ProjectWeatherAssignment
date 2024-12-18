package com.example.projectweatherassignment.ui.search

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetCountriesUseCase
import kotlinx.coroutines.Job
import androidx.lifecycle.viewModelScope
import com.example.common.Resources
import com.example.domain.usecase.GetMultipleCitiesWeatherUseCase
import com.example.domain.usecase.SaveCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getMultipleCitiesWeatherUseCase: GetMultipleCitiesWeatherUseCase,
    private val saveCurrentWeatherUseCase: SaveCurrentWeatherUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(600L)

                    if (event.query.isNotBlank())
                    getCountries(event.query)
                }
            }

            is SearchEvent.OnCountryListResult -> {
                getMultipleCitiesCurrentWeather(event.citiesNames)
            }

            is SearchEvent.OnSaveSelectedCity -> {
                saveCurrentWeatherUseCase(event.currentWeather)
            }
        }
    }

    fun getCountries(query: String) = viewModelScope.launch {
        getCountriesUseCase(query).collect { result ->
            when (result) {
                is Resources.Loading -> {
                    state = state.copy(
                        isLoading = true
                    )
                }

                is Resources.Success -> {

                    val result = result.data

                    if (result.isNullOrEmpty()) {
                        state = state.copy(
                            countries = emptyList(),
                            isLoading = false,
                            isEmpty = true
                        )
                    } else{
                        state = state.copy(
                            countries = result,
                            isLoading = false,
                            isEmpty = false
                        )
                    }


                }

                is Resources.Error -> {
                    state = state.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun getMultipleCitiesCurrentWeather(
        cities: List<String>,
    ) = viewModelScope.launch {
        getMultipleCitiesWeatherUseCase(cities).collect { result ->

            when (result) {
                is Resources.Loading -> {
                    state = state.copy(
                        isLoading = true
                    )
                }

                is Resources.Success -> {

                    val result = result.data

                    if (result == null) {
                        state = state.copy(
                            currentWeatherList = result,
                            isLoading = false,
                            isEmpty = true
                        )
                    } else
                        state = state.copy(
                            currentWeatherList = result,
                            isLoading = false,
                            isEmpty = false
                        )
                }

                is Resources.Error -> {
                    state = state.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }


}