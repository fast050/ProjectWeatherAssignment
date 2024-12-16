package com.example.domain.usecase

import com.example.common.Resources
import com.example.data.repository.WeatherRepository
import com.example.data.repository.model.CountryInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCountriesUseCase @Inject constructor(private val repository: WeatherRepository) {
   operator fun invoke(query: String) : Flow<Resources<List<CountryInfo>>> =
       repository.getCountries(query)
}