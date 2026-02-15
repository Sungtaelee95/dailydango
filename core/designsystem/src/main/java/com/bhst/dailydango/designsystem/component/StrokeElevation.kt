package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun StrokeElevation(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    elevation: Dp = 4.dp,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Surface(
        modifier = modifier
            .clickable(onClick = onClick, enabled = enabled),
        shape = shape,
        color = backgroundColor,
        shadowElevation = elevation,
        border = BorderStroke(1.dp, outlineColor)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = contentAlignment
        ) {
            content()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StrokeElevationPreview() {
    DailyDangoTheme {
        DailyDangoTheme {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. 기본 사용 (Default)
                Text("Default", style = MaterialTheme.typography.labelMedium)
                StrokeElevation(
                    modifier = Modifier.size(width = 200.dp, height = 56.dp),
                    onClick = {}
                ) {
                    Text(
                        text = "기본 버튼",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}