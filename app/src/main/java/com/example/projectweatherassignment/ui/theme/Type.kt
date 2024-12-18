package com.example.projectweatherassignment.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.projectweatherassignment.R

val poppingFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold , FontWeight.Bold),
    Font(R.font.poppins_semi_bold , FontWeight.SemiBold),
    Font(R.font.poppins_medium , FontWeight.Medium),
    Font(R.font.poppins_thin , FontWeight.Thin)
)


// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = poppingFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 70.sp,
        lineHeight = 105.sp,
        color = Black2C
    ),
    headlineSmall = TextStyle(
        fontFamily = poppingFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 45.sp,
        letterSpacing = 0.5.sp,
        color = Black2C
    ),
    titleSmall = TextStyle(
        fontFamily = poppingFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = poppingFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 22.5.sp,
        letterSpacing = 0.5.sp,
        color = Black2C
    ),
    bodySmall = TextStyle(
        fontFamily = poppingFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp,
        color = GrayC4
    ),
    labelSmall = TextStyle(
        fontFamily = poppingFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 8.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp
    )
)