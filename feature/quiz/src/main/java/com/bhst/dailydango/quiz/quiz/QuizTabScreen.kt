package com.bhst.dailydango.quiz.quiz

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.quiz.R
import com.bhst.dailydango.designsystem.component.ColorBar
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.component.QuizCard
import com.bhst.dailydango.designsystem.component.QuizTabCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.quiz.QuizContentState

@Composable
fun QuizTabScreen(
    chapter: Int,
    back: () -> Unit = {},
    viewModel: QuizViewModel = hiltViewModel()
) {
    val quizContents by viewModel.quizContents.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getQuizContent(chapter = chapter)
    }
    QuizTabContent(
        quizContents = quizContents,
        onOptionClick = viewModel::updateQuizContent,
        speakerClick = viewModel::soundPlay,
        back = back,
        chapter = chapter
    )
}

@Composable
fun QuizTabContent(
    quizContents: List<QuizContentState> = emptyList(),
    onOptionClick: (QuizContentState) -> Unit = {},
    speakerClick: (Uri?) -> Unit = {},
    back: () -> Unit = {},
    chapter: Int = 1
) {
    val pagerState = rememberPagerState(pageCount = { quizContents.size + 1 })
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ColorBar(
            color = MaterialTheme.colorScheme.onSecondaryFixedVariant
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$chapter" + stringResource(R.string.chapter_of_quiz),
                    style = DailyDangoTheme.typography.bold20,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val currentPage = pagerState.currentPage + 1
        val totalPages = quizContents.size + 1
        val progress = currentPage.toFloat() / totalPages.toFloat()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .width(600.dp),
            ) {
                // 프로그레스 바
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)), // 양끝을 둥글게 처리
                    color = MaterialTheme.colorScheme.onSecondaryFixedVariant, // 채워지는 색상 (테마에 맞게 수정 가능)
                    trackColor = MaterialTheme.colorScheme.surfaceContainerHigh, // 배경 색상
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 진행률 텍스트 (예: 2/10)
                Text(
                    text = "$currentPage/$totalPages",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    textAlign = TextAlign.End,
                    style = DailyDangoTheme.typography.bold12, // 가지고 계신 폰트 스타일에 맞게 조정하세요
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    // 카드 양옆에 약간의 여백을 주어 중앙에 배치되게 합니다.
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    // 페이지(카드) 간의 간격을 설정합니다.
                    pageSpacing = 16.dp,
                    verticalAlignment = Alignment.Top
                ) { pageIndex ->
                    // 현재 페이지 인덱스에 해당하는 퀴즈 데이터를 가져옵니다.
                    if (pageIndex >= quizContents.size) {
                        QuizEndTabScreen(
                            back = back
                        )
                    } else {
                        val quizState = quizContents[pageIndex]

                        // 기존에 작성하신 QuizCard 컴포저블을 호출합니다.
                        QuizTabCard(
                            quizState = quizState,
                            optionClick = onOptionClick,
                            speakerClick = speakerClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuizEndTabScreen(
    back: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        ImageCard(
            painter = painterResource(R.drawable.quiz_end_img),
            contentDescription = stringResource(R.string.quiz_end),
            modifier = Modifier
                .width(200.dp)
                .height(360.dp)
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Text(
            text = stringResource(R.string.quiz_end_message),
            style = DailyDangoTheme.typography.bold16,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Button(
            onClick = back,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onSecondaryFixedVariant
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.back),
                style = DailyDangoTheme.typography.bold28,
                color = MaterialTheme.colorScheme.inverseSurface
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = TABLET)
fun QuizTabContentPreview() {
    DailyDangoTheme {
        QuizTabContent()
    }
}