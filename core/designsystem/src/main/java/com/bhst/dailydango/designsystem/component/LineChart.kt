package com.bhst.dailydango.designsystem.component

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun LineChart(
    values: List<Int> = List(10) { (60..100).random() },
    modifier: Modifier = Modifier,
    maxValue: Int = 100,
    midValue: Int = 50,
) {
    if (values.isEmpty()) return

    val scrollState = rememberScrollState()
    val dayWidth: Dp = 40.dp
    val extraWidth: Dp = 80.dp
    val contentWidth = dayWidth * values.size + extraWidth

    // === 테마 색/타이포 가져오기 ===
    val primary = MaterialTheme.colorScheme.primary
    val gridStrong = primary
    val gridMedium = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.9f)
    val gridLight = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)
    val axisTextColor = MaterialTheme.colorScheme.surfaceContainerHigh
    val pointLabelColor = MaterialTheme.colorScheme.onSurface
    val surface = MaterialTheme.colorScheme.surface

    val density = LocalDensity.current
    val labelOffset = with(density) { 14.dp.toPx() }
    val textSizePx = with(density) { 10.sp.toPx() }

    val textPaint = remember {
        Paint().apply {
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
    }.also {
        it.textSize = textSizePx
        it.color = pointLabelColor.toArgb()
    }

    val canvasTopPadding = 24.dp
    val canvasBottomPadding = 40.dp
    var canvasHeight by remember { mutableStateOf(0f) }

    val animation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    // [수정 2] 데이터 개수(values.size)가 변경될 때마다 스크롤을 끝으로 이동
    LaunchedEffect(values.size) {
        if (values.isNotEmpty()) {
            // 데이터가 추가되어 화면이 갱신된 후 스크롤 이동
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }

    val progress = animation.value

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .horizontalScroll(scrollState) // 스크롤 상태 연결
    ) {
        Box(
            modifier = Modifier
                .width(contentWidth)
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
        ) {
            // ... (이하 Canvas 및 UI 그리기 코드는 기존과 동일) ...

            // ====== 그래프 영역 ======
            Canvas(
                modifier = Modifier
                    .matchParentSize()
                    .padding(end = 60.dp, top = canvasTopPadding, bottom = canvasBottomPadding)
                    .onSizeChanged { canvasHeight = it.height.toFloat() }
            ) {
                val width = size.width
                val height = size.height

                val topY = height * 0.15f
                val midY = height * 0.50f
                val bottomY = height * 0.85f

                fun drawDashedLine(y: Float, color: Color, stroke: Float = 2f) {
                    val dash = 14f
                    val gap = 8f
                    var x = 0f
                    while (x < width) {
                        drawLine(
                            color = color,
                            start = Offset(x, y),
                            end = Offset(x + dash, y),
                            strokeWidth = stroke
                        )
                        x += dash + gap
                    }
                }

                drawDashedLine(topY, gridStrong)
                drawDashedLine(midY, gridMedium)
                drawDashedLine(bottomY, gridLight, stroke = 1.5f)

                if (values.size >= 2) {
                    val chartHeight = bottomY - topY
                    val xStep = width / (values.size - 1).toFloat()

                    val path = Path()

                    values.forEachIndexed { index, value ->
                        val baseX = xStep * index
                        val x = baseX * progress
                        val ratio = (value.toFloat() / maxValue.toFloat()).coerceIn(0f, 1f)
                        val y = bottomY - chartHeight * ratio

                        if (index == 0) path.moveTo(x, y)
                        else path.lineTo(x, y)
                    }

                    drawPath(
                        path = path,
                        color = primary,
                        style = Stroke(width = 4f)
                    )

                    values.forEachIndexed { index, value ->
                        val baseX = xStep * index
                        val x = baseX * progress
                        val ratio = (value.toFloat() / maxValue.toFloat()).coerceIn(0f, 1f)
                        val y = bottomY - chartHeight * ratio

                        drawCircle(color = surface, radius = 6f, center = Offset(x, y))
                        drawCircle(color = primary, radius = 4f, center = Offset(x, y))

                        drawContext.canvas.nativeCanvas.drawText(
                            value.toString(),
                            x,
                            y - labelOffset,
                            textPaint
                        )
                    }
                }
            }

            // Y축 (오른쪽 100 고정)
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(top = canvasTopPadding, bottom = canvasBottomPadding, end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = maxValue.toString(), color = primary, style = MaterialTheme.typography.labelSmall)
                Spacer(Modifier.weight(1f))
            }

            // X축 (날짜 라벨)
            val daysCount = values.size
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(end = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (day in 1..daysCount) {
                    Box(modifier = Modifier.width(dayWidth).height(24.dp), contentAlignment = Alignment.Center) {
                        Text(text = day.toString(), color = axisTextColor, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

            // midValue (50)
            if (canvasHeight > 0f) {
                val midYRatio = 0.5f
                val midYpx = canvasHeight * midYRatio
                val midYdp = with(density) { midYpx.toDp() }

                Text(
                    text = midValue.toString(),
                    color = axisTextColor,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 8.dp)
                        .offset(y = canvasTopPadding + midYdp)
                )
            }
        }
    }
}

@Preview
@Composable
fun LineChartPreview() {
    DailyDangoTheme {
        LineChart()
    }
}
