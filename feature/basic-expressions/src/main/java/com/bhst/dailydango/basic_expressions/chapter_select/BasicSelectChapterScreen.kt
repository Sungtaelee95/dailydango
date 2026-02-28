package com.bhst.dailydango.basic_expressions.chapter_select

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.ChapterCard
import com.bhst.dailydango.model.chapter.Chapter

@Composable
fun BasicExpressionsScreen(
    viewModel: BasicSelectChapterViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getChapters()
    }
    BasicExpressionsContent(
        chapters = uiState.value
    )
}

@Composable
fun BasicExpressionsContent(
    chapters: List<Chapter> = emptyList()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        items(chapters) { chapter ->
            ChapterCard(
                chapter = chapter.title,
                imgUrl = chapter.imgUrl,
                subTitle = chapter.subTitle,
                onClick = {}
            )
        }
    }
}