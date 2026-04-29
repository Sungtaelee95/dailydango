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
    primary = DailyDangoColor.VividPink,
    onPrimary = DailyDangoColor.RosePink,
    primaryContainer = DailyDangoColor.VividBlue,
    secondary = DailyDangoColor.VividOrange,
    onSecondary = DailyDangoColor.Apricot,
    secondaryContainer = DailyDangoColor.ForestGreen,
    onSecondaryContainer = DailyDangoColor.MintWhite,
    tertiary = DailyDangoColor.SkyBlue,
    onTertiary = DailyDangoColor.PaleBlue,
    background = DailyDangoColor.DarkGray,
    onBackground = DailyDangoColor.White,
    surface = DailyDangoColor.LightDarkGray,
    surfaceVariant = DailyDangoColor.NearBlack,
    surfaceTint = DailyDangoColor.Charcoal,
    inverseSurface = DailyDangoColor.White1,
    scrim = DailyDangoColor.WhiteSmoke,
    surfaceBright = DailyDangoColor.DeepGray,
    surfaceContainer = DailyDangoColor.MutedRose,
    surfaceContainerHigh = DailyDangoColor.SilverMist,
    surfaceContainerHighest = DailyDangoColor.MutedSand,
    primaryFixed = DailyDangoColor.WildStrawberry,
    onPrimaryFixedVariant = DailyDangoColor.SlateBlue,
    primaryFixedDim = DailyDangoColor.VeryDarkGray,
    onPrimaryFixed = DailyDangoColor.CoralPink,
    secondaryFixed = DailyDangoColor.BrownGray,
    secondaryFixedDim= DailyDangoColor.LightOrange,
    onSecondaryFixed = DailyDangoColor.LightMint,
    onSecondaryFixedVariant = DailyDangoColor.CharcoalMint,
    tertiaryFixed = DailyDangoColor.NeonGreen,
    tertiaryFixedDim = DailyDangoColor.DeepMint,
)

// 🔵 라이트 테마 컬러 스킴
private val LightColorScheme = lightColorScheme(
    primary = DailyDangoColor.VividPink,
    onPrimary = DailyDangoColor.RosePink,
    primaryContainer = DailyDangoColor.VividBlue,
    secondary = DailyDangoColor.VividOrange,
    onSecondary = DailyDangoColor.Apricot,
    secondaryContainer = DailyDangoColor.ForestGreen,
    onSecondaryContainer = DailyDangoColor.MintWhite,
    tertiary = DailyDangoColor.SkyBlue,
    onTertiary = DailyDangoColor.PaleBlue,
    background = DailyDangoColor.ShellPink,
    onBackground = DailyDangoColor.Black,
    surface = DailyDangoColor.White,
    surfaceVariant = DailyDangoColor.LightYellow,
    surfaceTint = DailyDangoColor.PaleGray,
    inverseSurface = DailyDangoColor.White1,
    scrim = DailyDangoColor.LightBlack,
    surfaceBright = DailyDangoColor.MistyPink,
    surfaceContainer = DailyDangoColor.Wood, // 표에 명시된 내용 추가
    surfaceContainerHigh = DailyDangoColor.SilverMist,
    surfaceContainerHighest = DailyDangoColor.MutedSand,
    primaryFixed = DailyDangoColor.WildStrawberry,
    onPrimaryFixedVariant = DailyDangoColor.SoftBlue,
    primaryFixedDim = DailyDangoColor.PinkLace,
    onPrimaryFixed = DailyDangoColor.SalmonPink,
    secondaryFixed = DailyDangoColor.PearlBush,
    secondaryFixedDim = DailyDangoColor.Orange,
    onSecondaryFixed = DailyDangoColor.LightMint,
    onSecondaryFixedVariant = DailyDangoColor.MidiumGreen,
    tertiaryFixed = DailyDangoColor.AshGreen,
    tertiaryFixedDim = DailyDangoColor.MintGreen
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