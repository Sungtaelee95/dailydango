package com.bhst.dailydango.basic_expressions.chapter_select

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.ChapterCard
import com.bhst.dailydango.designsystem.component.ChapterTabCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.chapter.Chapter

@Composable
fun BasicExpressionsTabScreen(
    navigateToChapter: (Int) -> Unit = {},
    viewModel: BasicSelectChapterViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getChapters()
    }
    BasicExpressionsTabContent(
        navigateToChapter = navigateToChapter,
        chapters = uiState.value
    )
}

@Composable
fun BasicExpressionsTabContent(
    navigateToChapter: (Int) -> Unit = {},
    chapters: List<Chapter> = emptyList()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(chapters) { index, chapter ->
            ChapterTabCard(
                chapter = chapter,
                onClick = {
                    navigateToChapter(chapter.title.toInt())
                },
                modifier = Modifier.width(480.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = TABLET)
fun BasicExpressionsContentTabPreview() {
    DailyDangoTheme {
        BasicExpressionsTabContent()
    }
}