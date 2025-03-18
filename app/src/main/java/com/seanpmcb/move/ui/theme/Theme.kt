package com.seanpmcb.move.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = DeepMoss,
    onPrimary = SoftWarmWhite,
    primaryContainer = MutedSage,
    onPrimaryContainer = DarkMoss,
    
    secondary = CoolTealGray,
    onSecondary = SoftWarmWhite,
    secondaryContainer = SoftTeal,
    onSecondaryContainer = DarkMoss,
    
    tertiary = WarmSandBeige,
    onTertiary = DarkMoss,
    
    background = SoftWarmWhite,
    onBackground = DarkMoss,
    
    surface = SoftWarmWhite,
    onSurface = DarkMoss,
    surfaceVariant = DustyGrayGreen,
    onSurfaceVariant = DarkMoss,
    
    outline = CoolTealGray
)

private val DarkColorScheme = darkColorScheme(
    primary = MutedSage,
    onPrimary = DarkMoss,
    primaryContainer = DeepMoss,
    onPrimaryContainer = LightSage,
    
    secondary = SoftTeal,
    onSecondary = DarkMoss,
    secondaryContainer = CoolTealGray,
    onSecondaryContainer = LightSage,
    
    tertiary = WarmSandBeige,
    onTertiary = DarkMoss,
    
    background = DarkMoss,
    onBackground = SoftWarmWhite,
    
    surface = DarkMoss,
    onSurface = SoftWarmWhite,
    surfaceVariant = DeepMoss,
    onSurfaceVariant = LightSage,
    
    outline = MutedSage
)

@Composable
fun MoveTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // We don't need to set statusBarColor as enableEdgeToEdge() handles it
            // Just set the appearance based on the theme
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MoveTypography,
        shapes = Shapes,
        content = content
    )
}