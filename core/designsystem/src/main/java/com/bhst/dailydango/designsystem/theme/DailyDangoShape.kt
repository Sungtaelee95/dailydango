package com.bhst.dailydango.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Stable
data class DailyDangoShape(
    val chip: RoundedCornerShape = RoundedCornerShape(10.dp),
    val rounded12: RoundedCornerShape = RoundedCornerShape(12.dp),
)

val LocalShape = staticCompositionLocalOf { DailyDangoShape() }
