package com.bhst.dailydango.designsystem.component

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.quiz.QuizContentState
import com.bhst.dailydango.model.quiz.QuizOptionState

@Composable
fun QuizCard(
    quizState: QuizContentState,
    optionClick: (Int) -> Unit = {},
    speakerSize: Int = 24,
    speakerClick: (Uri?) -> Unit = {},
    modifier: Modifier = Modifier
) {
    DailyDangoOutLineCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (quizState.soundUri != null) {
                    SpeakerAnimatedIcon(
                        onClick = { speakerClick(quizState.soundUri) }
                    )
                }
                Text(
                    text = quizState.title,
                    style = DailyDangoTheme.typography.bold16,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            if (quizState.subTitle.isNotEmpty()) {
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Text(
                    text = quizState.subTitle,
                    style = DailyDangoTheme.typography.medium16,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(
                        items = quizState.options,
                        key = { it.number }
                    ) {
                        QuizOptionCard(
                            option = it,
                            optionClick = optionClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuizOptionCard(
    option: QuizOptionState,
    optionClick: (Int) -> Unit = {},
) {
    when {
        option.correct && option.isOpen -> { // 정답이 열려있을 때

        }

        !option.correct && option.isOpen -> { // 오답이 열려있을 때

        }

        else -> { // 열려 있지 않을 때
            DailyDangoOutLineCard(
                onClick ={
                    optionClick(option.number)
                }
            ) {
                Column(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = option.label,
                        style = DailyDangoTheme.typography.medium16,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun QuizCardPreview() {
    DailyDangoTheme {
        QuizCard(
            quizState = QuizContentState(
                id = "123123",
                title = "퀴즈 타이틀 예시입니다.",
                subTitle = "다음 빈칸에 _____ 올바른 것은?",
                soundName = "test-exp",
                soundUri = null,
                options = listOf(
                    QuizOptionState(
                        number = 1,
                        label = "사과",
                        correct = false,
                        explanation = "사과는 올바르지 않습니다.",
                        isOpen = false
                    ),
                    QuizOptionState(
                        number = 2,
                        label = "배",
                        correct = false,
                        explanation = "배는 올바르지 않습니다.",
                        isOpen = false
                    ),
                    QuizOptionState(
                        number = 3,
                        label = "복숭아",
                        correct = true,
                        explanation = "복숭아는 정답입니다.",
                        isOpen = true
                    )
                ),
                order = 1
            )
        )
    }
}