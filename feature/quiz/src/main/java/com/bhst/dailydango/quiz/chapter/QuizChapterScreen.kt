package com.bhst.dailydango.quiz.chapter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.ChapterCard
import com.bhst.dailydango.designsystem.component.PaginationControls
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.chapter.Chapter
import kotlinx.coroutines.launch
import kotlin.math.ceil

@Composable
fun QuizChapterScreen(
    navigateToChapter: (Int) -> Unit = {},
    viewModel: QuizChapterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getChapters()
    }
    QuizChapterContent(
        navigateToChapter = navigateToChapter,
        chapters = uiState
    )
}

@Composable
fun QuizChapterContent(
    navigateToChapter: (Int) -> Unit = {},
    chapters: List<Chapter> = emptyList()
) {
// 💡 한 페이지당 보여줄 아이템 개수
    val itemsPerPage = 5

    // 💡 총 페이지 수 계산
    val totalPages = maxOf(1, ceil(chapters.size.toDouble() / itemsPerPage).toInt())

    // 💡 Pager 상태 관리 (페이지는 0부터 시작)
    val pagerState = rememberPagerState(pageCount = { totalPages })

    // 코루틴 스코프 (하단 버튼 클릭 시 Pager 스크롤 애니메이션을 위해 필요)
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        // 💡 좌우 스와이프가 가능한 Pager 영역
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            // 각 페이지(0-indexed)에 해당하는 데이터 잘라내기
            val startIndex = page * itemsPerPage
            val endIndex = minOf(startIndex + itemsPerPage, chapters.size)
            val currentChapters = if (chapters.isNotEmpty()) {
                chapters.subList(startIndex, endIndex)
            } else {
                emptyList()
            }

            // 챕터 리스트 영역 (현재 페이지 데이터만 렌더링)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp)
            ) {
                items(currentChapters) { chapter ->
                    ChapterCard(
                        chapter = chapter,
                        onClick = {
                            navigateToChapter(chapter.title.toInt())
                        },
                        circleColor = MaterialTheme.colorScheme.onSecondaryFixed,
                        tagContainerColor = MaterialTheme.colorScheme.onSecondaryFixedVariant
                    )
                }
            }
        }

        // 페이지네이션 컨트롤 영역 (데이터가 있을 때만 표시)
        if (totalPages > 1) {
            PaginationControls(
                // Pager는 0부터 시작하므로 +1을 해서 UI(1, 2, 3...)에 맞춤
                currentPage = pagerState.currentPage + 1,
                totalPages = totalPages,
                onPageSelected = { selectedPage ->
                    // 버튼을 누르면 해당 페이지로 스크롤 이동 (ui는 1부터 시작하므로 -1 적용)
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(selectedPage - 1)
                    }
                },
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun QuizChapterContentPreview() {
    DailyDangoTheme {
        QuizChapterContent(
            chapters = listOf(Chapter(
                title = "타이틀"
            ))
        )
    }
}