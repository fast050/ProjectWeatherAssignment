package com.example.projectweatherassignment.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectweatherassignment.ui.theme.Black2C

@Composable
fun DegreeDisplay(
    modifier: Modifier = Modifier,
    temperatureNumberValue: Double,
    temperatureNumberTextFontStyle: TextStyle = MaterialTheme.typography.headlineLarge,
    temperatureNumberTextFontSize: TextUnit = 70.sp,
    temperatureNumberTextFont: FontWeight = SemiBold,
    temperatureSymbolFontSize: TextUnit = 25.sp,
    temperatureSymbolFontWeight: FontWeight = Normal,
    temperatureNumberFontColor: Color = Black2C,
    temperatureSymbolFontColor: Color = Black2C,
    spaceBetweenSymbolAndNumber: Dp = 8.dp,
    symbolOffSet: Dp = 1.dp
) {
    Row(
        verticalAlignment = Alignment.Top, // Aligns degree symbol to the top
        modifier = modifier // Adds spacing around the text
    ) {
        Text(
            text = temperatureNumberValue.toInt().toString(),
            style = temperatureNumberTextFontStyle,
            fontWeight = temperatureNumberTextFont,
            fontSize = temperatureNumberTextFontSize,
            color = temperatureNumberFontColor
        )
        Text(
            text = "°",
            fontSize = temperatureSymbolFontSize, // Smaller font size
            fontWeight = temperatureSymbolFontWeight, // Normal weight
            color = temperatureSymbolFontColor,
            modifier = Modifier
                .padding(start = spaceBetweenSymbolAndNumber) // Small space between "31" and "°"
                .offset(y = symbolOffSet) // Move degree symbol upwards
        )
    }
}