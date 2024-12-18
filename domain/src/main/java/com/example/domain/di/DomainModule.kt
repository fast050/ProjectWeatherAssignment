package com.example.domain.di

import com.example.data.repository.WeatherRepository
import com.example.domain.usecase.GetCountriesUseCase
import com.example.domain.usecase.GetLocalCurrentWeatherUseCase
import com.example.domain.usecase.GetMultipleCitiesWeatherUseCase
import com.example.domain.usecase.SaveCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(repository: WeatherRepository): GetCountriesUseCase =
        GetCountriesUseCase(repository)


    @Provides
    @Singleton
    fun provideGetCurrentWeatherUseCase(repository: WeatherRepository): GetLocalCurrentWeatherUseCase =
        GetLocalCurrentWeatherUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveCurrentWeatherUseCase(repository: WeatherRepository): SaveCurrentWeatherUseCase =
        SaveCurrentWeatherUseCase(repository)

    @Provides
    @Singleton
    fun provideGetMultipleCitiesWeatherUseCase(repository: WeatherRepository): GetMultipleCitiesWeatherUseCase =
        GetMultipleCitiesWeatherUseCase(repository)

}