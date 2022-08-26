package com.github.janruz.spacexapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val Typography.title: TextStyle get() = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    letterSpacing = 0.sp
)

val Typography.label: TextStyle get() =  TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    letterSpacing = 0.15.sp
)