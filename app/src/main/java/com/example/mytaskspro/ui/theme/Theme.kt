package com.example.mytaskspro.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

enum class AppThemeMode(val title: String) {
    SYSTEM("System Default"),
    DARK("Modern Dark"),
    LIGHT("Classic Light"),
    OLED("OLED Pitch Black"),
    CYBERPUNK("Cyberpunk Neon"),
    SUNSET("Sunset Glow"),
    FOREST("Emerald Forest")
}

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

private val OledColorScheme = darkColorScheme(
    primary = OledPrimary,
    secondary = OledSecondary,
    background = OledBackground,
    surface = OledSurface,
    surfaceVariant = Color(0xFF1E1E1E)
)

private val CyberpunkColorScheme = darkColorScheme(
    primary = CyberpunkPrimary,
    secondary = CyberpunkSecondary,
    background = CyberpunkBackground,
    surface = CyberpunkSurface,
    surfaceVariant = Color(0xFF2B1254)
)

private val SunsetColorScheme = darkColorScheme(
    primary = SunsetPrimary,
    secondary = SunsetSecondary,
    background = SunsetBackground,
    surface = SunsetSurface,
    surfaceVariant = Color(0xFF3D1F49)
)

private val ForestColorScheme = darkColorScheme(
    primary = ForestPrimary,
    secondary = ForestSecondary,
    background = ForestBackground,
    surface = ForestSurface,
    surfaceVariant = Color(0xFF2D6A4F)
)

@Composable
fun MyTasksProTheme(
    themeMode: AppThemeMode = AppThemeMode.SYSTEM,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val systemInDark = isSystemInDarkTheme()

    val colorScheme = when (themeMode) {
        AppThemeMode.SYSTEM -> {
            if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (systemInDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            } else {
                if (systemInDark) DarkColorScheme else LightColorScheme
            }
        }
        AppThemeMode.LIGHT -> LightColorScheme
        AppThemeMode.DARK -> DarkColorScheme
        AppThemeMode.OLED -> OledColorScheme
        AppThemeMode.CYBERPUNK -> CyberpunkColorScheme
        AppThemeMode.SUNSET -> SunsetColorScheme
        AppThemeMode.FOREST -> ForestColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
