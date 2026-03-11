package com.bhst.dailydango.basic_expressions.chapter_tip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.basic.expressions.R
import com.bhst.dailydango.designsystem.component.DailyDangoElevationCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.tip.Tip

@Composable
fun ChapterTipScreen(
    chapter: Int,
    navigateToSentenceStudy: (Int) -> Unit = {},
    navigateToWordStudy: (Int) -> Unit,
    viewModel: ChapterTipViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getChapterTip(chapter)
    }
    ChapterTipContent(
        tips = uiState,
        chapter = chapter,
        navigateToSentenceStudy = navigateToSentenceStudy,
        navigateToWordStudy = navigateToWordStudy
    )
}

@Composable
fun ChapterTipContent(
    tips: List<Tip> = emptyList(),
    chapter: Int = 0,
    navigateToSentenceStudy: (Int) -> Unit = {},
    navigateToWordStudy: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(tips) { tip ->
                DailyDangoElevationCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = 20.dp,
                    elevation = 4.dp,
                    contentColor = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = tip.title,
                            style = DailyDangoTheme.typography.bold20,
                            color = MaterialTheme.colorScheme.secondaryFixedDim
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = tip.content,
                            style = DailyDangoTheme.typography.medium16,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navigateToSentenceStudy(chapter) },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Text(
                    text = stringResource(R.string.sentence_study),
                    style = DailyDangoTheme.typography.bold24,
                    color = MaterialTheme.colorScheme.secondaryFixedDim
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = { navigateToWordStudy(chapter) },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryFixedDim
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(R.string.word_study),
                    style = DailyDangoTheme.typography.bold24,
                    color = MaterialTheme.colorScheme.surface,
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ChapterTipContentPreview() {
    DailyDangoTheme {
        ChapterTipContent(
            tips = listOf(
                Tip(
                    title = "인사는 대화의 시작입니다!",
                    content = "시간과 상황에 맞게 사용하면 더 자연스러워요."
                )
            )
        )
    }
}
