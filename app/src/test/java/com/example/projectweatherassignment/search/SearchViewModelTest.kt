package com.example.projectweatherassignment.search

import com.example.common.Resources
import com.example.common.model.CountryInfo
import com.example.domain.usecase.GetCountriesUseCase
import com.example.domain.usecase.GetMultipleCitiesWeatherUseCase
import com.example.domain.usecase.SaveCurrentWeatherUseCase
import com.example.projectweatherassignment.ui.search.SearchViewModel
import com.example.projectweatherassignment.util.countryInfoFakeList
import com.example.projectweatherassignment.util.currentWeatherFakeList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SearchViewModelTest {

    private lateinit var getCountriesUseCase: GetCountriesUseCase
    private lateinit var getMultipleCitiesWeatherUseCase: GetMultipleCitiesWeatherUseCase
    private lateinit var saveCurrentWeatherUseCase: SaveCurrentWeatherUseCase
    private lateinit var viewModel: SearchViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        getCountriesUseCase = mock()
        getMultipleCitiesWeatherUseCase = mock()
        saveCurrentWeatherUseCase = mock()
        viewModel = SearchViewModel(
            getCountriesUseCase,
            getMultipleCitiesWeatherUseCase,
            saveCurrentWeatherUseCase
        )

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `test getCountries return list of countries success`() = runTest{

        `when`(getCountriesUseCase("shar"))
            .thenReturn(flowOf(Resources.Success(countryInfoFakeList)))

        viewModel.getCountries("shar")

        val actual = viewModel.state.countries
        val expected = countryInfoFakeList

        assertEquals(expected , actual)
    }

    @Test
    fun `test getCountries return empty`() = runTest{

        `when`(getCountriesUseCase("shar"))
            .thenReturn(flowOf(Resources.Success(null)))

        viewModel.getCountries("shar")

        val actual = viewModel.state.countries
        val expected = emptyList<CountryInfo>()

        assertEquals(expected , actual)
    }

    @Test
    fun `test getMultipleCitiesCurrentWeather return list of current weather success`() = runTest{
        val cities = listOf("Dubai", "Sharjah")
        `when`(getMultipleCitiesWeatherUseCase(cities))
            .thenReturn(flowOf(Resources.Success(currentWeatherFakeList)))

        viewModel.getMultipleCitiesCurrentWeather(cities)

        val actual = viewModel.state.currentWeatherList
        val expected = currentWeatherFakeList

        assertEquals(expected , actual)
    }

    @Test
    fun `test getMultipleCitiesCurrentWeather return null failed`() = runTest{
        val cities = listOf("Dubai", "Sharjah")
        `when`(getMultipleCitiesWeatherUseCase(cities))
            .thenReturn(flowOf(Resources.Success(null)))

        viewModel.getMultipleCitiesCurrentWeather(cities)

        val actual = viewModel.state.currentWeatherList
        val expected = null

        assertEquals(expected , actual)
    }
}

