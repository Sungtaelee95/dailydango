package com.bhst.dailydango.play_repeat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.menu.R
import com.bhst.dailydango.designsystem.component.ColorBar
import com.bhst.dailydango.designsystem.component.DailyDangoElevationCard
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import kotlin.math.roundToInt

@Composable
fun PlayRepeatTabScreen(
    viewModel: PlayRepeatViewModel = hiltViewModel()
) {
    val repeat by viewModel.playRepeat.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getPlayRepeat()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.soundPlayRelease()
        }
    }

    // 1. Content 컴포저블 연결 및 ViewModel 함수 전달
    PlayRepeatTabContent(
        repeat = repeat,
        onRepeatChange = { viewModel.setPlayRepeat(it) },
        playTestExpression = { viewModel.playTestExpression(it) }
    )
}

@Composable
fun PlayRepeatTabContent(
    repeat: Int = 1,
    onRepeatChange: (Int) -> Unit = {},
    playTestExpression: (String) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ColorBar(
            color = MaterialTheme.colorScheme.tertiaryFixedDim,
            height = 60
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                ImageCard(
                    painter = painterResource(R.drawable.play_repeate_img),
                    contentDescription = "Speed",
                    modifier = Modifier
                        .size(60.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(R.string.repeat_control),
                    style = DailyDangoTheme.typography.bold24,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(480.dp)
                .padding(start = 28.dp, end = 28.dp, top = 12.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            RepeatControlTabCard(
                repeat = repeat,
                onRepeatChange = onRepeatChange
            )

            TestExpressionsTabCard(
                playTestExpression = playTestExpression
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatControlTabCard(
    repeat: Int = 1,
    onRepeatChange: (Int) -> Unit = {}
) {
    DailyDangoElevationCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = 16.dp,
        color = MaterialTheme.colorScheme.surfaceBright
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = stringResource(R.string.now_repeat) + " " + repeat.toString() +
                        stringResource(R.string.repeat_count), // 예: "현재 반복 3회"
                style = DailyDangoTheme.typography.bold20,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))

            Slider(
                value = repeat.toFloat(), // Slider는 기본적으로 Float을 사용하므로 변환
                onValueChange = { onRepeatChange(it.roundToInt()) }, // 다시 Int로 안전하게 변환하여 콜백
                valueRange = 1f..5f, // 3. 최소 1회 ~ 최대 5회로 범위 수정
                steps = 3, // 1과 5 사이의 눈금 개수 (2, 3, 4 -> 3개)
                modifier = Modifier.fillMaxWidth(),
                thumb = {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(MaterialTheme.colorScheme.surface, CircleShape)
                            .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(MaterialTheme.colorScheme.primary, CircleShape)
                        )
                    }
                },
                track = { sliderState ->
                    SliderDefaults.Track(
                        sliderState = sliderState,
                        modifier = Modifier.height(3.dp),
                        colors = SliderDefaults.colors(
                            activeTrackColor = MaterialTheme.colorScheme.primary,
                            inactiveTrackColor = MaterialTheme.colorScheme.primary,
                            activeTickColor = MaterialTheme.colorScheme.primary,
                            inactiveTickColor = MaterialTheme.colorScheme.primary
                        )
                    )
                },
                colors = SliderDefaults.colors(
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.primary,
                    activeTickColor = MaterialTheme.colorScheme.primary,
                    inactiveTickColor = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            // 4. 하단 텍스트 라벨 (1회 ~ 5회)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val labels = listOf("1", "2", "3", "4", "5")
                labels.forEach { label ->
                    Text(
                        text = label,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(32.dp),
                        textAlign = TextAlign.Center,
                        style = DailyDangoTheme.typography.medium16
                    )
                }
            }
        }
    }
}

@Composable
fun TestExpressionsTabCard(
    playTestExpression: (String) -> Unit = {}
) {
    DailyDangoElevationCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = 16.dp,
        color = MaterialTheme.colorScheme.surfaceBright
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.test_expression),
                style = DailyDangoTheme.typography.bold20,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                ImageCard(
                    painter = painterResource(com.bhst.dailydango.app.core.designsystem.R.drawable.speaker_24px),
                    contentDescription = "Speaker",
                    modifier = Modifier.size(24.dp),
                    onClick = { playTestExpression("今、日本語を勉強中です。") },
                    filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.test_expression_for_japan),
                        style = DailyDangoTheme.typography.medium16,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(R.string.test_expression_for_korea),
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = TABLET)
fun PlayRepeatTabContentPreview() {
    DailyDangoTheme {
        PlayRepeatTabContent()
    }
}