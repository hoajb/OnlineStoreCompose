package vn.hoanguyen.compose.onlinestore.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color.White, // Light color for contrast with dark background
    secondary = Color.Gray, // Lighter shade of gray for secondary elements
    tertiary = Color.Black, // Black for further contrast and flexibility

    // Other default colors to override
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black, // Black text on light primary color
    onSecondary = Color.White, // White text on light secondary color
    onTertiary = Color.White, // White text on black tertiary color
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    secondary = Color.Gray, // Lighter shade of gray to contrast with black
    tertiary = Color.White, // White for further contrast and flexibility

    // Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black, // Black text on light secondary color
    onTertiary = Color.Black, // Black text on white tertiary color
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun OnlineStoreComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}