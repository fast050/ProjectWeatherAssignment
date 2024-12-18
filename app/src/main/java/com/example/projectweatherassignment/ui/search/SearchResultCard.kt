package com.example.projectweatherassignment.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.common.model.CurrentWeather
import com.example.projectweatherassignment.ui.common.DegreeDisplay
import com.example.projectweatherassignment.ui.theme.GrayF2

@Composable
fun SearchResultCard(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = GrayF2)
            .padding(horizontal = 31.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.weight(.7f)
        ) {
            Text(
                text = currentWeather.cityName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = Bold
            )
            DegreeDisplay(
                temperatureNumberValue = currentWeather.temperatureByKelvin,
                temperatureNumberTextFontSize = 60.sp,
                modifier = Modifier
            )
        }


        AsyncImage(
            model = "https:${currentWeather.weatherConditionIcon}",
            contentDescription = currentWeather.weatherCondition,
            modifier = Modifier.size(83.dp).weight(.3f),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun SearchResultList(modifier: Modifier = Modifier ,
                     currentWeather: List<CurrentWeather>? ,
                     onItemClick :(currentWeather: CurrentWeather) -> Unit ={} ) {

    currentWeather?.let {
        LazyColumn(
            modifier = modifier ,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(currentWeather.size) { index ->
                SearchResultCard(
                    currentWeather = currentWeather[index],
                    modifier = Modifier.clickable{
                        onItemClick(currentWeather[index])
                    }
                )
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun PreviewSearchResultCard() {
    SearchResultCard(currentWeather = currentWeatherFake)
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSearchResultList() {
    SearchResultList(currentWeather = listOf(currentWeatherFake , currentWeatherFake , currentWeatherFake ,currentWeatherFake))
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