package com.bhst.dailydango.designsystem.theme


import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bhst.dailydango.app.core.designsystem.R

private val GmarketSansKr = FontFamily(
    Font(R.font.gmarket_sans_bold, FontWeight.Bold),
    Font(R.font.gmarket_sans_medium, FontWeight.Medium),
    Font(R.font.gmarket_sans_light, FontWeight.Light)
)

private val DailyDangoStyle = TextStyle(
    fontFamily = GmarketSansKr
)

internal val Typography = DailyDangoTypography(

    bold14 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bold16 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bold22 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    medium10 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    ),
    medium11 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    ),
    medium12 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    medium13 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp
    ),
    medium14 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    medium16 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    medium22 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    medium30 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp
    ),
    light9 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 9.sp
    ),
    light10 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    ),
    light12 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    light15 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 15.sp
    ),
    light16 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    light23 = DailyDangoStyle.copy(
        fontWeight = FontWeight.Light,
        fontSize = 23.sp
    ),
)

@Immutable
data class DailyDangoTypography(
    val bold14: TextStyle,
    val bold16: TextStyle,
    val bold22: TextStyle,


    val medium10: TextStyle,
    val medium11: TextStyle,
    val medium12: TextStyle,
    val medium13: TextStyle,
    val medium14: TextStyle,
    val medium16: TextStyle,
    val medium22: TextStyle,
    val medium30: TextStyle,

    val light9: TextStyle,
    val light10: TextStyle,
    val light12: TextStyle,
    val light15: TextStyle,
    val light16: TextStyle,
    val light23: TextStyle,
)

val LocalTypography = staticCompositionLocalOf {
    DailyDangoTypography(
        bold14 = DailyDangoStyle,
        bold16 = DailyDangoStyle,
        bold22 = DailyDangoStyle,

        medium10 = DailyDangoStyle,
        medium11 = DailyDangoStyle,
        medium12 = DailyDangoStyle,
        medium13 = DailyDangoStyle,
        medium14 = DailyDangoStyle,
        medium16 = DailyDangoStyle,
        medium22 = DailyDangoStyle,
        medium30 = DailyDangoStyle,

        light9 = DailyDangoStyle,
        light10 = DailyDangoStyle,
        light12 = DailyDangoStyle,
        light15 = DailyDangoStyle,
        light16 = DailyDangoStyle,
        light23 = DailyDangoStyle,
    )
}
