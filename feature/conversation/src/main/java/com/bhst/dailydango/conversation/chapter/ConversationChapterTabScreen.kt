package com.bhst.dailydango.conversation.chapter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.ChapterCard
import com.bhst.dailydango.designsystem.component.ChapterTabCard
import com.bhst.dailydango.designsystem.component.PaginationControls
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.chapter.Chapter
import kotlinx.coroutines.launch
import kotlin.math.ceil

@Composable
fun ConversationChapterTabScreen(
    navigateToChapter: (Int) -> Unit = {},
    viewModel: ConversationChapterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getChapters()
    }
    ConversationChapterTabContent(
        navigateToChapter = navigateToChapter,
        chapters = uiState
    )
}

@Composable
fun ConversationChapterTabContent(
    navigateToChapter: (Int) -> Unit = {},
    chapters: List<Chapter> = emptyList()
) {
    // 💡 한 페이지당 보여줄 아이템 개수
    val itemsPerPage = 6

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

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // 2열 고정
                    modifier = Modifier
                        .width(960.dp)
                        .padding(horizontal = 12.dp),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp), // 열 사이 간격
                    verticalArrangement = Arrangement.spacedBy(16.dp)    // 행 사이 간격
                ) {
                    items(currentChapters) { chapter ->
                        ChapterTabCard(
                            chapter = chapter,
                            onClick = {
                                navigateToChapter(chapter.title.toInt())
                            },
                            // 그리드 셀에 맞게 꽉 차도록 fillMaxWidth 사용 (기존 480.dp 대신 유동적 대응)
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
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
@Preview(showBackground = true, device = TABLET)
fun ConversationChapterTabContentPreview() {
    DailyDangoTheme {
        val fakeChapters = List(120) { index ->
            Chapter(title = (index + 1).toString() /* 나머지 파라미터 빈 값 처리 필요 */)
        }
        ConversationChapterTabContent(
            chapters = fakeChapters
        )
    }
}