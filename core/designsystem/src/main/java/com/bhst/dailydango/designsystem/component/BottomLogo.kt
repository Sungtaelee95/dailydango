package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoColor
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun BottomLogo(
    modifier: Modifier = Modifier,
    color: Color = DailyDangoColor.LightGray,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(BottomLogoHeight),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Droid Knights 2023",
            style = DailyDangoTheme.typography.bold14,
            color = color,
        )
    }
}

internal val BottomLogoHeight = 48.dp

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun BottomLogoPreview() {
    DailyDangoTheme {
        BottomLogo()
    }
}
