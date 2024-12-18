package com.example.domain.usecase

import com.example.common.Resources
import com.example.common.model.CurrentWeather
import com.example.data.repository.WeatherRepository
import com.example.domain.util.currentWeatherFake
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetMultipleCitiesWeatherUseCaseTest {

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetMultipleCitiesWeatherUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetMultipleCitiesWeatherUseCase(repository)
    }

    @Test
    fun `invoke calls getCurrentWeather for each city and returns success`() = runTest {
        val city1 = "sharjah"
        val city2 = "dubai"

        // Mock repository responses
        `when`(repository.getCurrentWeather(city1)).thenReturn(flow {
            emit(Resources.Success(currentWeatherFake))
        })
        `when`(repository.getCurrentWeather(city2)).thenReturn(flow {
            emit(Resources.Success(currentWeatherFake))
        })

        val cities = listOf(city1, city2)

        val result = useCase(cities).last().data

        val expected = listOf(currentWeatherFake, currentWeatherFake)


        assertEquals(expected, result)
    }

    @Test
    fun `invoke calls getMuiltipleCitiesWeather and get List of CurrentWeather All Success Api Calls And One Failed`() = runTest {
        val city1 = "sharjah"
        val city2 = "dubai"

        // Mock repository responses
        `when`(repository.getCurrentWeather(city1)).thenReturn(flow {
            emit(Resources.Success(currentWeatherFake.copy(cityName = "sharjah")))
        })
        `when`(repository.getCurrentWeather(city2)).thenReturn(flow {
            emit(Resources.Error(data = null, message = "Failed to fetch data for cities:currentWeatherFake"))
        })

        val cities = listOf(city1, city2)

        val result = useCase(cities).last().data

        val expected = listOf(currentWeatherFake.copy(cityName = "sharjah"))


        assertEquals(expected, result)
    }

}