package com.example.data.remote

import com.example.data.remote.dto.CountryInfoResponse
import com.example.data.remote.dto.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.data.BuildConfig


interface WeatherApi {

    @GET("search.json")
    suspend fun getCountries(
        @Query("q") query: String,
        @Query("key") key: String = BuildConfig.API_KEY,
    ) : List<CountryInfoResponse>

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") query: String,
        @Query("key") key: String = BuildConfig.API_KEY,
    ) : CurrentWeatherResponse


    companion object {
        const val BASE_URL = " http://api.weatherapi.com/v1/"
    }


}