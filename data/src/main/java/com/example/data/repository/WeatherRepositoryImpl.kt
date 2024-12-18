package com.example.data.repository

import com.example.common.Resources
import com.example.common.model.CountryInfo
import com.example.common.model.CurrentWeather
import com.example.data.local.WeatherSharedPreferences
import com.example.data.mapper.toCountryInfo
import com.example.data.mapper.toCurrentWeather
import com.example.data.remote.WeatherApi

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

    /**
     *  if query is null, get data from local storage
     *
     */
    override fun getCurrentWeather(query: String): Flow<Resources<CurrentWeather>> = flow {
        emit(Resources.Loading())

        try {
           val remoteCurrentWeather = remoteSource.getCurrentWeather(query = query)
           emit(Resources.Success(remoteCurrentWeather.toCurrentWeather()))
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

    override fun saveLocalWeather(currentWeather: CurrentWeather) {
        localSource.saveCurrentWeather(currentWeather) // save data in shared preference
    }

    override fun getLocalWeather(): CurrentWeather? {
        return localSource.getWeatherCurrent()
    }


}
