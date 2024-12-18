package com.example.projectweatherassignment.ui.search

import com.example.common.model.CurrentWeather


sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String): SearchEvent()
    data class OnCountryListResult(val citiesNames : List<String>): SearchEvent()
    data class OnSaveSelectedCity(val currentWeather: CurrentWeather): SearchEvent()
}