package com.example.domain.usecase

import com.example.data.repository.WeatherRepository
import com.example.domain.util.currentWeatherFake
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SaveCurrentWeatherUserCaseTest {

    private lateinit var repository: WeatherRepository
    private lateinit var useCaseSaveLocal: SaveCurrentWeatherUseCase
    private lateinit var useCaseGetLocal: GetLocalCurrentWeatherUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCaseSaveLocal = SaveCurrentWeatherUseCase(repository)
        useCaseGetLocal = GetLocalCurrentWeatherUseCase(repository)
    }

    @Test
    fun `invoke calls getLocalWeather from repository and success`() = runTest {

        `when`(repository.getLocalWeather())
            .thenReturn(currentWeatherFake)

        useCaseSaveLocal(currentWeatherFake) // save the currentWeatherFake

        val expected = currentWeatherFake
        val actual = useCaseGetLocal()

        assertEquals(expected , actual)
    }

    @Test
    fun `invoke calls getLocalWeather from repository and null`() = runTest {
        `when`(repository.getLocalWeather())
            .thenReturn(null)

        useCaseSaveLocal(currentWeatherFake) // save the currentWeatherFake

        val expected = null
        val actual = useCaseGetLocal()

        assertEquals(expected , actual)
    }


}