package com.example.projectweatherassignment.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.common.model.CurrentWeather
import com.example.projectweatherassignment.R
import com.example.projectweatherassignment.SearchScreenNav
import com.example.projectweatherassignment.ui.common.SearchBar
import com.example.projectweatherassignment.ui.theme.Black2C
import com.example.projectweatherassignment.ui.theme.ProjectWeatherAssignmentTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val coroutineScope = rememberCoroutineScope()

    ProjectWeatherAssignmentTheme {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeContent), // Ensure content respects system bars
            topBar = {
                SearchBar(
                    modifier = Modifier
                        .padding(
                            top = 44.dp,
                            start = 24.dp,
                            end = 24.dp
                        )
                        .clickable {
                            navController.navigate(SearchScreenNav)
                        },
                    isEnable = false,
                )
            },
            content = { innerPadding ->
                HomeContent(
                    modifier = Modifier
                        .padding(innerPadding),
                    isEmpty = viewModel.state.isEmpty,
                    currentWeather = viewModel.state.currentWeather
                )
            }
        )


        LaunchedEffect(Unit) {
            coroutineScope.launch{
                delay(5000L)

                if (viewModel.state.isEmpty)
                    navController.navigate(SearchScreenNav)
            }
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    isEmpty: Boolean,
    currentWeather: CurrentWeather?
) {
    Box(modifier) {
        if (isEmpty) {
            NoCitySelectedItem()
        } else {
            WeatherDetailItem(currentWeather = currentWeather)
        }
    }
}

/**
 *  show this view when we dont have saved current weather data
 */
@Composable
private fun NoCitySelectedItem() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.no_city_selected),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = SemiBold,
        )
        Text(
            text = stringResource(R.string.please_search_for_a_city),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = SemiBold,
            color = Black2C,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}



@Preview(showSystemUi = true)
@Composable
fun PreviewHomeContent() {
    ProjectWeatherAssignmentTheme {
        HomeContent(
            isEmpty = false,
            currentWeather = currentWeatherFake
        )
    }
}

private val currentWeatherFake = CurrentWeather(
    cityName = "Hyderabad",
    temperatureByKelvin = 31.00,
    humidity = 25,
    uv = 2,
    feelsLikeTemperatureByKelvin = 20.55,
    weatherCondition = "Sunny",
    weatherConditionIcon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
)
