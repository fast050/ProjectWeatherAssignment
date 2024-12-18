package com.example.domain.usecase

import com.example.common.Resources
import com.example.data.repository.WeatherRepository
import com.example.domain.util.countryInfoFakeList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetCountriesUseCaseTest {

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetCountriesUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetCountriesUseCase(repository)
    }

    @Test
    fun `invoke calls getCountries from repository and success`() = runTest {

        `when`(repository.getCountries("Sharjah"))
            .thenReturn(flow { emit(Resources.Success(countryInfoFakeList)) })

        val expected = Resources.Success(countryInfoFakeList).data
        val actual = useCase("Sharjah").last().data

        assertEquals(expected , actual)
    }

    @Test
    fun `invoke calls getCountries from repository and error`() = runTest {

        `when`(repository.getCountries("Sharjah"))
            .thenReturn(
                flow { emit(Resources.Error(null , "Check your internet connection"))}
            )

        val expected = Resources.Error(null , "Check your internet connection").message
        val actual = useCase("Sharjah").last().message

        assertEquals(expected , actual)
    }

}