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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.quiz.QuizContentState
import com.bhst.dailydango.model.quiz.QuizOptionState

@Composable
fun QuizCard(
    quizState: QuizContentState,
    optionClick: (QuizContentState) -> Unit = {},
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
                .padding(16.dp),
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
                        size = 24,
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
            }
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
                ) { option ->
                    QuizOptionCard(
                        quizState = quizState,
                        option = option,
                        optionClick = optionClick
                    )
                }
            }
        }
    }
}

@Composable
fun QuizOptionCard(
    quizState: QuizContentState,
    option: QuizOptionState,
    optionClick: (QuizContentState) -> Unit = {},
) {
    when {
        option.isOpen -> { // 열려있을 때
            if (option.correct) { // 정답인 경우
                DailyDangoOutLineCard(
                    borderColor = MaterialTheme.colorScheme.onSecondaryFixedVariant,
                    color = MaterialTheme.colorScheme.onSecondaryFixed,
                    onClick ={
                        val newOptions = quizState.options.map { if (it == option) it.copy(isOpen = !it.isOpen) else it }
                        optionClick(quizState.copy(options = newOptions))
                    }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Text(
                            text = option.label,
                            style = DailyDangoTheme.typography.bold16,
                            color = MaterialTheme.colorScheme.onSecondaryFixedVariant,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = stringResource(R.string.answer),
                            style = DailyDangoTheme.typography.bold16,
                            color = MaterialTheme.colorScheme.onSecondaryFixedVariant
                        )
                        if (option.explanation.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = option.explanation,
                                style = DailyDangoTheme.typography.medium16,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            } else { // 오답인 경우
                DailyDangoOutLineCard(
                    borderColor = MaterialTheme.colorScheme.primaryFixed,
                    onClick ={
                        val newOptions = quizState.options.map { if (it == option) it.copy(isOpen = !it.isOpen) else it }
                        optionClick(quizState.copy(options = newOptions))
                    }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Text(
                            text = option.label,
                            style = DailyDangoTheme.typography.bold16,
                            color = MaterialTheme.colorScheme.primaryFixed,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = stringResource(R.string.not_answer),
                            style = DailyDangoTheme.typography.bold16,
                            color = MaterialTheme.colorScheme.primaryFixed
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = option.explanation,
                            style = DailyDangoTheme.typography.medium16,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }

        else -> { // 열려 있지 않을 때
            DailyDangoOutLineCard(
                onClick ={
                    val newOptions = quizState.options.map { if (it == option) it.copy(isOpen = !it.isOpen) else it }
                    optionClick(quizState.copy(options = newOptions))
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
                        isOpen = true
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