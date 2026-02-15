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
    primary = DailyDangoColor.BrandBlue,
    onPrimary = DailyDangoColor.White,
    primaryContainer = DailyDangoColor.MidnightSlate,
    onPrimaryContainer = DailyDangoColor.SoftAshWhite,
    inversePrimary = DailyDangoColor.BrandBlue,

    secondary = DailyDangoColor.Green,
    onSecondary = DailyDangoColor.White,
    secondaryContainer = DailyDangoColor.GrayBlue,
    onSecondaryContainer = DailyDangoColor.White,
    secondaryFixed = DailyDangoColor.ActiveBlue,
    secondaryFixedDim = DailyDangoColor.NeutralBlue,
    onSecondaryFixed = DailyDangoColor.VitalAmber,

    tertiary = DailyDangoColor.VitalAmber,
    tertiaryFixed = DailyDangoColor.StableActionBlue,
    tertiaryFixedDim = DailyDangoColor.CautionOrange,
    onTertiaryFixed = DailyDangoColor.AlertRed,
    onTertiary = DailyDangoColor.Yellow01,
    tertiaryContainer = DailyDangoColor.Yellow04,
    onTertiaryContainer = DailyDangoColor.FrostedSkyBlue,

    error = DailyDangoColor.Red02,
    onError = DailyDangoColor.Red05,
    errorContainer = DailyDangoColor.Red04,
    onErrorContainer = DailyDangoColor.Red01,

    surface = DailyDangoColor.SoftSlateNavy,
    onSurface = DailyDangoColor.ClearSkyBlue,
    surfaceVariant = DailyDangoColor.DimSlate,
    onSurfaceVariant = DailyDangoColor.LightGray,

    background = DailyDangoColor.CharcoalBlue,
    onBackground = DailyDangoColor.White,
    surfaceDim = DailyDangoColor.MutedAshGray,
    surfaceContainerLowest = DailyDangoColor.Black,
    surfaceContainerLow = DailyDangoColor.Graphite,
    surfaceContainerHigh = DailyDangoColor.DuskGray,

    inverseSurface = DailyDangoColor.LightWhite,
    inverseOnSurface = DailyDangoColor.Black,

    outline = DailyDangoColor.CoolCharcoal,
    outlineVariant = DailyDangoColor.MutedSlate,

    scrim = DailyDangoColor.Black,
)


// 🔵 라이트 테마 컬러셈
private val LightColorScheme = lightColorScheme(
    primary = DailyDangoColor.BrandBlue,
    onPrimary = DailyDangoColor.White,
    primaryContainer = DailyDangoColor.White,
    onPrimaryContainer = DailyDangoColor.SoftBlack,
    inversePrimary = DailyDangoColor.BrandBlue,

    secondary = DailyDangoColor.Green,              // 보조색: 화이트
    onSecondary = DailyDangoColor.Black,        // 화이트 위의 텍스트는 블루
    secondaryContainer = DailyDangoColor.White,
    onSecondaryContainer = DailyDangoColor.NneutralGray,
    secondaryFixed = DailyDangoColor.ActiveBlue,
    secondaryFixedDim = DailyDangoColor.NeutralBlue,
    onSecondaryFixed = DailyDangoColor.SubtleSky,

    tertiary = DailyDangoColor.VitalAmber,
    onTertiary = DailyDangoColor.Black,
    tertiaryContainer = DailyDangoColor.Yellow03A40,
    onTertiaryContainer = DailyDangoColor.SkyBlue,
    tertiaryFixed = DailyDangoColor.StableActionBlue,
    tertiaryFixedDim = DailyDangoColor.CautionOrange,
    onTertiaryFixed = DailyDangoColor.AlertRed,

    error = DailyDangoColor.Red03,
    onError = DailyDangoColor.White,
    errorContainer = DailyDangoColor.Red01,
    onErrorContainer = DailyDangoColor.Red06,

    surface = DailyDangoColor.White,
    onSurface = DailyDangoColor.BrightSkyBlue,
    surfaceVariant = DailyDangoColor.BlueGray,
    onSurfaceVariant = DailyDangoColor.DarkGray,

    background = DailyDangoColor.PaleBlue,
    onBackground = DailyDangoColor.Black,

    surfaceDim = DailyDangoColor.MidGray,
    surfaceContainerLowest = DailyDangoColor.PaleGray,
    surfaceContainerLow = DailyDangoColor.PaleGray,
    surfaceContainerHigh = DailyDangoColor.LightGray,

    inverseSurface = DailyDangoColor.BrandBlueDark,
    inverseOnSurface = DailyDangoColor.White,

    outline = DailyDangoColor.CoolBlueGray,
    outlineVariant = DailyDangoColor.LightGray,

    scrim = DailyDangoColor.Black,
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
