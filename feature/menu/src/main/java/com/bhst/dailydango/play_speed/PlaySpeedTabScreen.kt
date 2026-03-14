package com.bhst.dailydango.play_speed

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun PlaySpeedTabScreen(
    viewModel: PlaySpeedViewModel = hiltViewModel()
) {
    val speed by viewModel.playSpeed.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getPlaySpeed()
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.soundPlayRelease()
        }
    }
    PlaySpeedTabContent(
        speed = speed,
        onSpeedChange = viewModel::setPlaySpeed,
        playTestExpression = viewModel::playTestExpression
    )
}

@Composable
fun PlaySpeedTabContent(
    speed: Float = 1.0f,
    onSpeedChange: (Float) -> Unit = {},
    playTestExpression: (String) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ColorBar(
            color = MaterialTheme.colorScheme.secondaryFixed,
            height = 60
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                ImageCard(
                    painter = painterResource(R.drawable.speed_img),
                    contentDescription = "speed_img",
                    modifier = Modifier
                        .size(60.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(R.string.speed_control),
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
            SpeedControlTabCard(
                speed = speed,
                onSpeedChange = onSpeedChange
            )
            TestExpressionsTabCard(
                playTestExpression = playTestExpression
            )
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
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                ImageCard(
                    painter = painterResource(com.bhst.dailydango.app.core.designsystem.R.drawable.speaker_24px),
                    contentDescription = "Speaker",
                    modifier = Modifier
                        .size(28.dp),
                    onClick = { playTestExpression("今、日本語を勉強中です。") },
                    filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeedControlTabCard(
    speed: Float = 1.0f,
    onSpeedChange: (Float) -> Unit = {}
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
                .padding(24.dp)
        ) {
            Text(
                text = stringResource(R.string.now_speed) + " " + speed.toString() +
                        stringResource(R.string.x),
                style = DailyDangoTheme.typography.bold20,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Slider(
                value = speed,
                onValueChange = { onSpeedChange(it) },
                valueRange = 0.5f..2.0f,
                steps = 2,
                modifier = Modifier.fillMaxWidth(),
                // 1. 커스텀 손잡이 (Thumb) 디자인
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
                        modifier = Modifier.height(3.dp), // ⭐ 라인 두께 조절 (기본값보다 얇게 설정, 원하는 수치로 변경 가능)
                        colors = SliderDefaults.colors(
                            activeTrackColor = MaterialTheme.colorScheme.primary,

                            // 💡 Tip: 비활성 트랙을 primary로 하면 전체가 한 색상으로 보일 수 있으므로
                            // 배경색(surface)이나 옅은 색(surfaceVariant)으로 두는 것을 추천합니다.
                            inactiveTrackColor = MaterialTheme.colorScheme.primary,

                            activeTickColor = MaterialTheme.colorScheme.primary, // ⭐ 선택된 뒤(지나간 후)에도 눈금 라인이 보이게 설정
                            inactiveTickColor = MaterialTheme.colorScheme.primary // 안 지나간 눈금선 색상
                        )
                    )
                },
                // 2. 트랙 및 눈금(Tick) 색상 설정
                colors = SliderDefaults.colors(
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.primary,
                    activeTickColor = MaterialTheme.colorScheme.primary, // 활성화된 구간의 눈금은 숨김 (사진과 동일)
                    inactiveTickColor = MaterialTheme.colorScheme.primary // 비활성화된 구간(오른쪽)의 눈금은 하얀색으로 표시
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            // 3. 하단 텍스트 라벨 (0.5, 1.0, 1.5, 2.0)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val labels = listOf("0.5", "1.0", "1.5", "2.0")
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
@Preview(showBackground = true, device = TABLET)
fun PlaySpeedContentTabPreview() {
    DailyDangoTheme {
        PlaySpeedTabContent()
    }
}