package com.example.domain.di

import com.example.data.repository.WeatherRepository
import com.example.domain.usecase.GetCountriesUseCase
import com.example.domain.usecase.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton


@InstallIn(ViewModelComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(repository: WeatherRepository): GetCountriesUseCase =
        GetCountriesUseCase(repository)


    @Provides
    @Singleton
    fun provideGetCurrentWeatherUseCase(repository: WeatherRepository): GetCurrentWeatherUseCase =
        GetCurrentWeatherUseCase(repository)

}