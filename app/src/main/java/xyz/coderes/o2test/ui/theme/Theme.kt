package xyz.coderes.o2test.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Blue500,
    onPrimary = White,
    background = White,
    surface = White,
    onSurface = Gray950,
    error = Red600,
    onError = White,
    secondary = Yellow700,
    onSecondary = Gray950
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue500,
    onPrimary = White,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    error = Red600,
    onError = White,
    secondary = Yellow700,
    onSecondary = White
)

@Composable
fun O2testTheme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}