package com.bhst.dailydango.basic_expressions.sentnece_study

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.content.Content

@Composable
fun SentenceStudyScreen(
    chapter: Int,
    viewModel: SentenceStudyViewModel = hiltViewModel()
) {
    val contents by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getSentenceContent(chapter)
    }
    SentenceStudyContent(
        contents = contents
    )
}

@Composable
fun SentenceStudyContent(
    contents: List<Content> = emptyList()
) {

}

@Composable
@Preview
fun SentenceStudyScreenPreview() {
    DailyDangoTheme {
        SentenceStudyContent()
    }
}