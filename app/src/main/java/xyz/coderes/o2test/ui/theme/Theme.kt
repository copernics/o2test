package xyz.coderes.o2test.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

val LightColorScheme = lightColorScheme(
    primary = AppColors.Surface.Brand,
    onPrimary = AppColors.Content.OnNeutralLow,
    background = AppColors.Surface.XLow,
    onBackground = AppColors.Content.OnNeutralXxHigh,
    surface = AppColors.Surface.XLow,
    onSurface = AppColors.Content.OnNeutralMedium,
    error = AppColors.Surface.Danger,
    onError = AppColors.Content.OnNeutralDanger,
    primaryContainer = AppColors.Surface.Brand,
    onPrimaryContainer = AppColors.Content.OnNeutralXxHigh,
    inversePrimary = AppColors.Content.OnNeutralMedium
)

val DarkColorScheme = darkColorScheme(
    primary = AppColors.Surface.Brand,
    onPrimary = AppColors.Content.OnNeutralLow,
    background = AppColors.Surface.XHigh,
    onBackground = AppColors.Content.OnNeutralLow,
    surface = AppColors.Surface.XHigh,
    onSurface = AppColors.Content.OnNeutralMedium,
    error = AppColors.Surface.Danger,
    onError = AppColors.Content.OnNeutralDanger,
    primaryContainer = AppColors.Surface.Brand,
    onPrimaryContainer = AppColors.Content.OnNeutralLow,
    inversePrimary = AppColors.Content.OnNeutralMedium
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
    CompositionLocalProvider(
        LocalSpacing provides AppSpacing(),
        LocalShapes provides AppShapes(),
        LocalTypography provides AppTypography,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = MaterialTheme.typography,
            content = content
        )
    }
}