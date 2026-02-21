package com.bhst.dailydango.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.glance.GlanceTheme
import androidx.glance.color.ColorProvider
import androidx.glance.color.colorProviders

// 🔵 다크 테마 컬러셈
private val DarkColorScheme = darkColorScheme(

)


// 🔵 라이트 테마 컬러셈
private val LightColorScheme = lightColorScheme(
    primary = DailyDangoColor.LightPink,
    onPrimary = DailyDangoColor.Coral,
    primaryContainer = DailyDangoColor.White2,
    onPrimaryContainer = DailyDangoColor.Black2,
    onSecondaryContainer = DailyDangoColor.Black1,
    background = DailyDangoColor.BackLight,
    onBackground = DailyDangoColor.Black1,
)


val LocalDarkTheme = compositionLocalOf { true }

@Composable
fun DailyDangoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    if (!LocalInspectionMode.current) {
        val view = LocalView.current
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalTypography provides Typography,
        LocalShape provides DailyDangoShape(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}

object DailyDangoTheme {
    val typography: DailyDangoTypography
        @Composable
        get() = LocalTypography.current

    val shape: DailyDangoShape
        @Composable
        get() = LocalShape.current
}

private val WidgetColorProviers = colorProviders(
    primary = ColorProvider(LightColorScheme.primary, DarkColorScheme.primary),
    onPrimary = ColorProvider(LightColorScheme.onPrimary, DarkColorScheme.onPrimary),
    primaryContainer = ColorProvider(
        LightColorScheme.primaryContainer,
        DarkColorScheme.primaryContainer
    ),
    onPrimaryContainer = ColorProvider(
        LightColorScheme.onPrimaryContainer,
        DarkColorScheme.onPrimaryContainer
    ),
    inversePrimary = ColorProvider(LightColorScheme.inversePrimary, DarkColorScheme.inversePrimary),
    secondary = ColorProvider(LightColorScheme.secondary, DarkColorScheme.secondary),
    onSecondary = ColorProvider(LightColorScheme.onSecondary, DarkColorScheme.onSecondary),
    secondaryContainer = ColorProvider(
        LightColorScheme.secondaryContainer,
        DarkColorScheme.secondaryContainer
    ),
    onSecondaryContainer = ColorProvider(
        LightColorScheme.onSecondaryContainer,
        DarkColorScheme.onSecondaryContainer
    ),
    tertiary = ColorProvider(LightColorScheme.tertiary, DarkColorScheme.tertiary),
    onTertiary = ColorProvider(LightColorScheme.onTertiary, DarkColorScheme.onTertiary),
    tertiaryContainer = ColorProvider(
        LightColorScheme.tertiaryContainer,
        DarkColorScheme.tertiaryContainer
    ),
    onTertiaryContainer = ColorProvider(
        LightColorScheme.onTertiaryContainer,
        DarkColorScheme.onTertiaryContainer
    ),
    error = ColorProvider(LightColorScheme.error, DarkColorScheme.error),
    onError = ColorProvider(LightColorScheme.onError, DarkColorScheme.onError),
    errorContainer = ColorProvider(LightColorScheme.errorContainer, DarkColorScheme.errorContainer),
    onErrorContainer = ColorProvider(
        LightColorScheme.onErrorContainer,
        DarkColorScheme.onErrorContainer
    ),
    surface = ColorProvider(LightColorScheme.surface, DarkColorScheme.surface),
    onSurface = ColorProvider(LightColorScheme.onSurface, DarkColorScheme.onSurface),
    inverseSurface = ColorProvider(LightColorScheme.inverseSurface, DarkColorScheme.inverseSurface),
    inverseOnSurface = ColorProvider(
        LightColorScheme.inverseOnSurface,
        DarkColorScheme.inverseOnSurface
    ),
    outline = ColorProvider(LightColorScheme.outline, DarkColorScheme.outline),
    background = ColorProvider(LightColorScheme.background, DarkColorScheme.background),
    onBackground = ColorProvider(LightColorScheme.onBackground, DarkColorScheme.onBackground),
    surfaceVariant = ColorProvider(LightColorScheme.surfaceVariant, DarkColorScheme.surfaceVariant),
    onSurfaceVariant = ColorProvider(
        LightColorScheme.onSurfaceVariant,
        DarkColorScheme.onSurfaceVariant
    )
)

@Composable
fun KnightsGlanceTheme(
    content: @Composable () -> Unit,
) {
    GlanceTheme(
        colors = WidgetColorProviers,
        content = content
    )
}
