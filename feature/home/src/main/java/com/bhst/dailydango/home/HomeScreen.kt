package com.bhst.dailydango.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bhst.dailydango.app.feature.home.R
import com.bhst.dailydango.designsystem.component.DailyDangoElevationBrushCard
import com.bhst.dailydango.designsystem.component.DailyDangoElevationCard
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.component.NotOutLineSearchField
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun HomeScreen(
    navigateToHiraganaKatakanaTip: () -> Unit = {},
    navigateToGrammarStudy: () -> Unit = {},
    navigateToGrammarTest: () -> Unit = {},
    navigateToSearch: () -> Unit = {}
) {
    HomeContent(
        navigateToHiraganaKatakanaTip = navigateToHiraganaKatakanaTip,
        navigateToGrammarStudy = navigateToGrammarStudy,
        navigateToGrammarTest = navigateToGrammarTest,
        navigateToSearch = navigateToSearch
    )
}

@Composable
fun HomeContent(
    navigateToHiraganaKatakanaTip: () -> Unit = {},
    navigateToGrammarStudy: () -> Unit = {},
    navigateToGrammarTest: () -> Unit = {},
    navigateToSearch: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .verticalScroll(scrollState),
    ) {
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        SearchContent(
            onClick = navigateToSearch
        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row {
                Text(
                    text = stringResource(R.string.hiragana_katacana),
                    style = DailyDangoTheme.typography.bold16,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.study_do_it),
                    style = DailyDangoTheme.typography.medium16,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            JapaneseStudyContent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(R.string.hiragana_katakana_study),
                subtitle = stringResource(R.string.strong_foundation),
                onClick = navigateToHiraganaKatakanaTip,
                painter = painterResource(R.drawable.hiragana_katakana_study),
            )

            JapaneseStudyContent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(R.string.learn_basic_expressions),
                subtitle = stringResource(R.string.with_conversation),
                leftColor = MaterialTheme.colorScheme.secondary,
                rightColor = MaterialTheme.colorScheme.onSecondary,
                onClick = navigateToGrammarStudy,
                painter = painterResource(R.drawable.basic_expressions_img)
            )

            JapaneseStudyContent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(R.string.take_skill_test),
                leftColor = MaterialTheme.colorScheme.tertiary,
                rightColor = MaterialTheme.colorScheme.onTertiary,
                painter = painterResource(R.drawable.level_test_img)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.for_me),
                    style = DailyDangoTheme.typography.medium16
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.level_up),
                    style = DailyDangoTheme.typography.bold16
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.do_it),
                    style = DailyDangoTheme.typography.medium16
                )
            }
        }
    }
}

@Composable
fun JapaneseStudyContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    title: String,
    subtitle: String = "",
    leftColor: Color = MaterialTheme.colorScheme.primary,
    rightColor: Color = MaterialTheme.colorScheme.onPrimary,
    painter: Painter = painterResource(R.drawable.hiragana_study_img)
) {
    DailyDangoElevationBrushCard(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = 20.dp,
        leftColor = leftColor,
        rightColor = rightColor,
        elevation = 8.dp,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 12.dp)
        ) {

            // 3. 이미지 영역
            ImageCard(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(112.dp),
                painter = painter,
                contentDescription = "학습 캐릭터 이미지",
                onClick = onClick,
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        top = 20.dp,
                        bottom = 20.dp,
                    ),
                verticalArrangement = Arrangement.Top
            ) {
                // 타이틀 영역 (padding start 유지)
                Row(
                    modifier = Modifier
                        .fillMaxWidth() // 너비 꽉 채우기
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = title,
                        style = DailyDangoTheme.typography.bold20,
                        color = MaterialTheme.colorScheme.inverseSurface,
                        lineHeight = 32.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 2. 부제목 영역 (오른쪽만 라디우스 적용)
                if (subtitle.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .background(
                                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(
                                    topStart = 0.dp,
                                    bottomStart = 0.dp,
                                    topEnd = 20.dp,
                                    bottomEnd = 20.dp
                                )
                            )
                            .padding(start = 16.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = subtitle,
                            style = DailyDangoTheme.typography.light16,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchContent(
    onClick: () -> Unit = {}
) {
    DailyDangoElevationCard(
        modifier = Modifier
            .fillMaxWidth(),
        shape = 24.dp,
        elevation = 8.dp,
        onClick = onClick
    ) {
        NotOutLineSearchField(
            hint = R.string.hint_search_word_or_grammar,
            enabled = false
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    DailyDangoTheme {
        HomeContent()
    }
}