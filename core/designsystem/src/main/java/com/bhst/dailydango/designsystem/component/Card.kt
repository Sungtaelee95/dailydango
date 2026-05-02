package com.bhst.dailydango.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun DailyDangoCard(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = color,
        contentColor = contentColor,
        shape = RoundedCornerShape(32.dp),
        shadowElevation = 2.dp,
        content = content,
    )
}

@Composable
fun DailyDangoElevationCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    elevation: Dp = 2.dp,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    shape: Dp = 2.dp,
    content: @Composable () -> Unit,
) {
    Surface(
        // Surface 자체의 onClick, enabled 속성은 제거하고 Modifier로 넘깁니다.
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, // ⭐ 물결 효과 제거 핵심
                enabled = enabled,
                onClick = onClick
            ),
        color = color,
        contentColor = contentColor,
        shape = RoundedCornerShape(shape),
        shadowElevation = elevation,
        content = content,
    )
}

@Composable
fun DailyDangoOutLineCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    borderColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    shape: Dp = 20.dp,
    content: @Composable () -> Unit,
) {
    Surface(
        // Surface 자체의 onClick, enabled 속성은 제거하고 Modifier로 넘깁니다.
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(shape),
                color = borderColor
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, // ⭐ 물결 효과 제거 핵심
                enabled = enabled,
                onClick = onClick
            ),
        color = color,
        contentColor = contentColor,
        shape = RoundedCornerShape(shape),
        content = content,
    )
}

@Composable
fun DailyDangoElevationBrushCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    elevation: Dp = 2.dp,
    leftColor: Color = MaterialTheme.colorScheme.onPrimary,
    rightColor: Color = MaterialTheme.colorScheme.primaryContainer,
    shape: Dp = 2.dp,
    content: @Composable () -> Unit,
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(leftColor, rightColor)
    )
    Surface(
        // Surface 자체의 onClick, enabled 속성은 제거하고 Modifier로 넘깁니다.
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, // ⭐ 물결 효과 제거 핵심
                enabled = enabled,
                onClick = onClick
            ),
        shape = RoundedCornerShape(shape),
        shadowElevation = elevation,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush)
        ) {
            content()
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun DailyDangoCardPreview() {
    DailyDangoTheme {
        DailyDangoCard(modifier = Modifier.size(320.dp, 160.dp), content = { })
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun DailyDangoOutLineCardPreview() {
    DailyDangoTheme {
        DailyDangoOutLineCard(modifier = Modifier.size(320.dp, 160.dp), content = { })
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun DailyDangoElvCardPreview() {
    DailyDangoTheme {
        DailyDangoElevationCard(modifier = Modifier.size(320.dp, 160.dp), content = { })
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun DailyDangoElvCardBrushPreview() {
    DailyDangoTheme {
        DailyDangoElevationBrushCard(modifier = Modifier.size(320.dp, 160.dp), content = { })
    }
}