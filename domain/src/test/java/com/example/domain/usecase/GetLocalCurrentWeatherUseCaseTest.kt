package com.example.domain.usecase

import com.example.common.Resources
import com.example.data.repository.WeatherRepository
import com.example.domain.util.currentWeatherFake
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetLocalCurrentWeatherUseCaseTest {

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetLocalCurrentWeatherUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetLocalCurrentWeatherUseCase(repository)
    }

    @Test
    fun `invoke calls getLocalWeather from repository and success`() = runTest {

        `when`(repository.getLocalWeather())
            .thenReturn(currentWeatherFake)

        val expected = currentWeatherFake
        val actual = useCase()

        assertEquals(expected , actual)
    }

    @Test
    fun `invoke calls getLocalWeather from repository and null`() = runTest {
        `when`(repository.getLocalWeather())
            .thenReturn(null)

        val expected = null
        val actual = useCase()

        assertEquals(expected , actual)
    }


}