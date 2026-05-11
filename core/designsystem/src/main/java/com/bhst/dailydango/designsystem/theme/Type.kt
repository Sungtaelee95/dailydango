package com.bhst.dailydango.designsystem.theme


import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.bhst.dailydango.app.core.designsystem.R

private val GmarketSansKr = FontFamily(
    Font(R.font.gmarket_sans_bold, FontWeight.Bold),
    Font(R.font.gmarket_sans_medium, FontWeight.Medium),
    Font(R.font.gmarket_sans_light, FontWeight.Light)
)

private val DailyDangoStyle = TextStyle(
    fontFamily = GmarketSansKr,
    lineHeight = 1.4.em
)

internal val Typography = DailyDangoTypography(

    bold12 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold14 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold16 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold20 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold22 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold24= DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold28= DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold32= DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold36 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold40 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold60 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    bold80 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 80.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium10 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium11 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium12 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium13 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium14 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.s
    ),
    medium16 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium18 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium20 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium22 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium24 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    medium30 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),

    light9 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 9.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    light10 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 10.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    light12 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    light15 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 15.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    light16 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    light20 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    light23 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 23.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
    light24 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        letterSpacing = TextUnit.Unspecified, // 또는 0.sp
    ),
)

@Immutable
data class DailyDangoTypography(
    val bold12: TextStyle,
    val bold14: TextStyle,
    val bold16: TextStyle,
    val bold20: TextStyle,
    val bold22: TextStyle,
    val bold24: TextStyle,
    val bold28: TextStyle,
    val bold32: TextStyle,
    val bold36: TextStyle,
    val bold40: TextStyle,
    val bold60: TextStyle,
    val bold80: TextStyle,

    val medium10: TextStyle,
    val medium11: TextStyle,
    val medium12: TextStyle,
    val medium13: TextStyle,
    val medium14: TextStyle,
    val medium16: TextStyle,
    val medium18: TextStyle,
    val medium20: TextStyle,
    val medium22: TextStyle,
    val medium24: TextStyle,
    val medium30: TextStyle,

    val light9: TextStyle,
    val light10: TextStyle,
    val light12: TextStyle,
    val light15: TextStyle,
    val light16: TextStyle,
    val light20: TextStyle,
    val light23: TextStyle,
    val light24: TextStyle,
)

val LocalTypography = staticCompositionLocalOf {
    DailyDangoTypography(
        bold12 = DailyDangoStyle,
        bold14 = DailyDangoStyle,
        bold16 = DailyDangoStyle,
        bold20 = DailyDangoStyle,
        bold22 = DailyDangoStyle,
        bold24 = DailyDangoStyle,
        bold28 = DailyDangoStyle,
        bold32 = DailyDangoStyle,
        bold36 = DailyDangoStyle,
        bold40 = DailyDangoStyle,
        bold60 = DailyDangoStyle,
        bold80 = DailyDangoStyle,

        medium10 = DailyDangoStyle,
        medium11 = DailyDangoStyle,
        medium12 = DailyDangoStyle,
        medium13 = DailyDangoStyle,
        medium14 = DailyDangoStyle,
        medium16 = DailyDangoStyle,
        medium18 = DailyDangoStyle,
        medium20 = DailyDangoStyle,
        medium22 = DailyDangoStyle,
        medium24 = DailyDangoStyle,
        medium30 = DailyDangoStyle,

        light9 = DailyDangoStyle,
        light10 = DailyDangoStyle,
        light12 = DailyDangoStyle,
        light15 = DailyDangoStyle,
        light16 = DailyDangoStyle,
        light20 = DailyDangoStyle,
        light23 = DailyDangoStyle,
        light24 = DailyDangoStyle
    )
}
