package com.bhst.dailydango.basic_expressions.chapter_tip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.DailyDangoElevationCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.tip.Tip

@Composable
fun ChapterTipScreen(
    chapter: Int,
    viewModel: ChapterTipViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getChapterTip(chapter)
    }
    ChapterTipContent(
        tips = uiState
    )
}

@Composable
fun ChapterTipContent(
    tips: List<Tip> = emptyList()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        items(tips) { tip ->
            DailyDangoElevationCard(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = 20.dp,
                elevation = 4.dp,
                contentColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 20.dp),
                ) {
                    Text(
                        text = tip.title,
                        style = DailyDangoTheme.typography.bold20,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = tip.content,
                        style = DailyDangoTheme.typography.light20,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
@Preview
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
