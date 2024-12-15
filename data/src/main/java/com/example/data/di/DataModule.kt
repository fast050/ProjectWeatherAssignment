package com.example.data.di

import android.content.Context
import com.example.data.local.WeatherSharedPreferences
import com.example.data.remote.WeatherApi
import com.example.data.repository.WeatherRepository
import com.example.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Provides
    @Singleton
    fun provideLogger() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttp(logger : HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(logger).build()

    @Provides
    @Singleton
    fun provideWeatherApi(okHttpClient: OkHttpClient) = Retrofit
        .Builder()
        .baseUrl(WeatherApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient) // to log the response
        .build().create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherDataStore(
       @ApplicationContext context: Context
    ) = WeatherSharedPreferences(context)


    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherApi,
        weatherSharePreferences: WeatherSharedPreferences
    ): WeatherRepository {
        return WeatherRepositoryImpl(api, weatherSharePreferences)
    }

}