package com.seanpmcb.move.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val MoveTypography = Typography(
    displayLarge = TextStyle(
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp,
        fontWeight = FontWeight.Light
    ),
    displayMedium = TextStyle(
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontSize = 48.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        fontSize = 34.sp,
        letterSpacing = 0.25.sp,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle(
        fontSize = 24.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        letterSpacing = 0.5.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.25.sp,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 1.25.sp,
        fontWeight = FontWeight.Medium
    )
)