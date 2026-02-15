package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

enum class DrivePeriodType {
    Daily,  // 일간
    Monthly // 월간
}

@Composable
fun DrivePeriodToggle(
    selected: DrivePeriodType,
    onSelectedChange: (DrivePeriodType) -> Unit,
) {
    val shape = RoundedCornerShape(999.dp)
    val bg = MaterialTheme.colorScheme.onPrimary
    val selectedBg = MaterialTheme.colorScheme.primary
    val selectedText = MaterialTheme.colorScheme.onPrimary
    val unselectedText = MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        modifier = Modifier
            .height(32.dp)
            .width(120.dp)
            .background(bg, shape)
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 일간
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(shape)
                .background(
                    if (selected == DrivePeriodType.Daily) selectedBg else Color.Transparent
                )
                .clickable { onSelectedChange(DrivePeriodType.Daily) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "일간",
                style = DailyDangoTheme.typography.bold14,
                color = if (selected == DrivePeriodType.Daily) selectedText else unselectedText
            )
        }

        // 월간
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(shape)
                .background(
                    if (selected == DrivePeriodType.Monthly) selectedBg else Color.Transparent
                )
                .clickable { onSelectedChange(DrivePeriodType.Monthly) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "월간",
                style = DailyDangoTheme.typography.bold14,
                color = if (selected == DrivePeriodType.Monthly) selectedText else unselectedText
            )
        }
    }
}
