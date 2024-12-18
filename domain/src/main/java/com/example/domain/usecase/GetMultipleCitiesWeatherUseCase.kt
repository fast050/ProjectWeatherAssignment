package com.example.domain.usecase

import com.example.common.Resources
import com.example.common.model.CurrentWeather
import com.example.data.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *  in this useCase we get the list<CurrentWeather> by call the
 *  repository.getCurrentWeather() for each city name and then get list of CurrentWeather
 */
class GetMultipleCitiesWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    val results = mutableListOf<CurrentWeather>() // Mutable list to store fetched results

    operator fun invoke(cities: List<String>): Flow<Resources<List<CurrentWeather>>> = flow {
        emit(Resources.Loading()) // Emit initial loading state
        results.clear()
        try {
            cities.asFlow().map { city ->
                repository.getCurrentWeather(query = city)
            }.collect { resource ->
                resource.collect { resultss ->
                    resultss.data?.let {
                        println("log list resource --> ${it.cityName} : ${it.temperatureByKelvin}")
                        results.add(it)
                    }
                }
            }

            emit(Resources.Success(results)) // Emit the final result
        } catch (e: Exception) {
            emit(Resources.Error(message = e.message ?: "Unknown error", data = null))
        }
    }


}