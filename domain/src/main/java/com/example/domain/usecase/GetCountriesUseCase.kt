package com.example.domain.usecase

import com.example.common.Resources
import com.example.common.model.CountryInfo
import com.example.data.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCountriesUseCase @Inject constructor(val repository: WeatherRepository) {
   operator fun invoke(query: String) : Flow<Resources<List<CountryInfo>>> =
       repository.getCountries(query)
}