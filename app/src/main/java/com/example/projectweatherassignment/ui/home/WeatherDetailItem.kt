package com.example.projectweatherassignment.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.common.model.CurrentWeather
import com.example.projectweatherassignment.R
import com.example.projectweatherassignment.ui.common.DegreeDisplay
import com.example.projectweatherassignment.ui.theme.Black2C
import com.example.projectweatherassignment.ui.theme.Gray9A
import com.example.projectweatherassignment.ui.theme.GrayC4
import com.example.projectweatherassignment.ui.theme.GrayF2

@Composable
fun WeatherDetailItem(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather?
) {
    if (currentWeather == null)
        return

    Column(
        modifier = modifier.fillMaxSize()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = "https:${currentWeather.weatherConditionIcon}",
            contentDescription = currentWeather.weatherCondition,
            modifier = Modifier.size(123.dp),
            contentScale = ContentScale.Crop
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = currentWeather.cityName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = SemiBold,
            )
            Icon(
                painter = painterResource(R.drawable.angle_arrow_ic),
                contentDescription = stringResource(R.string.location),
                tint = Black2C,
                modifier = Modifier
                    .size(21.dp)
            )
        }
        DegreeDisplay(
            modifier = Modifier.padding(16.dp),
            temperatureNumberValue = currentWeather.temperatureByKelvin
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(56.dp),
            modifier = Modifier
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = GrayF2)
                .padding(16.dp)

        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                Text(
                    text = "Humidity",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = Medium,
                    color = GrayC4
                )
                Text(
                    text = currentWeather.humidity.toString() + stringResource(R.string.percentage_symbol),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = Medium,
                    color = Gray9A

                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                Text(
                    text = "UV",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = Medium,
                    color = GrayC4
                )
                Text(
                    text = currentWeather.uv.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = Medium,
                    color = Gray9A

                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                Text(
                    text = "Feels like",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = Medium,
                    color = GrayC4
                )
                DegreeDisplay(
                    temperatureNumberValue = currentWeather.feelsLikeTemperatureByKelvin,
                    temperatureNumberTextFont = Medium,
                    temperatureSymbolFontWeight = SemiBold,
                    temperatureNumberTextFontSize = 15.sp,
                    temperatureNumberTextFontStyle = MaterialTheme.typography.bodyLarge,
                    temperatureSymbolFontSize = 15.sp,
                    temperatureNumberFontColor = Gray9A,
                    temperatureSymbolFontColor = Gray9A,
                    spaceBetweenSymbolAndNumber = .5.dp,
                    symbolOffSet = (-1).dp
                )
            }

        }
    }
}