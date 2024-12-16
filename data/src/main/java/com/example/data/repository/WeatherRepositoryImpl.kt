package com.example.data.repository

import com.example.common.Resources
import com.example.data.local.WeatherSharedPreferences
import com.example.data.mapper.toCountryInfo
import com.example.data.mapper.toCurrentWeather
import com.example.data.remote.WeatherApi
import com.example.data.repository.model.CountryInfo
import com.example.data.repository.model.CurrentWeather
import com.example.data.util.extractErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteSource: WeatherApi,
    private val localSource: WeatherSharedPreferences
) : WeatherRepository {


    override fun getCountries(query: String): Flow<Resources<List<CountryInfo>>> = flow {
        emit(Resources.Loading())
        try {
            val countriesList = remoteSource.getCountries(query = query)
            println("log : $countriesList")
            emit(
                Resources.Success(countriesList.map { it.toCountryInfo() })
            )
        }
        catch (e: HttpException) {
            emit(
                Resources.Error(
                    message = e.extractErrorMessage(),
                    data = null
                )
            )
        }
        catch (e: IOException) {
            emit(
                Resources.Error(
                    message = e.message ?: "Check your internet connection",
                    data = null
                )
            )
        }
    }


    override fun getCurrentWeather(query: String): Flow<Resources<CurrentWeather>> = flow {
        emit(Resources.Loading())

        val localCurrentWeather = localSource.getWeatherCurrent() // get data in shared preference

        try {
            val remoteCurrentWeather = remoteSource.getCurrentWeather(query = query)
            localSource.saveCurrentWeather(remoteCurrentWeather.toCurrentWeather()) // save data in shared preference

            emit(Resources.Success(remoteCurrentWeather.toCurrentWeather()))

        }
        catch (e: HttpException) {
            emit(
                Resources.Error(
                    message = e.extractErrorMessage(),
                    data = localCurrentWeather
                )
            )
        }
        catch (e: IOException) {
            emit(
                Resources.Error(
                    message = e.message ?: "Check your internet connection",
                    data = localCurrentWeather
                )
            )
        }
    }


}
