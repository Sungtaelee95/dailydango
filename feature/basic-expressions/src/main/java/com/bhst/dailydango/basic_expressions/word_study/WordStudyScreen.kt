package com.bhst.dailydango.basic_expressions.word_study

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.ContentCard
import com.bhst.dailydango.model.content.ContentState

@Composable
fun WordStudyScreen(
    chapter: Int,
    viewModel: WordStudyViewModel = hiltViewModel()
) {
    val contents by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getWordContent(chapter)
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.soundPlayRelease()
        }
    }
    WordStudyContent(
        contents = contents,
        updateSentenceContent = viewModel::updateSentenceContent,
        playAudio = viewModel::soundPlayForContent
    )
}

@Composable
fun WordStudyContent(
    contents: List<ContentState> = emptyList(),
    updateSentenceContent: (ContentState) -> Unit = {},
    playAudio: (Uri?) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(contents, key = { _, content -> content.japaneseTitle }) { index, content ->
            if (index == 0) Spacer(modifier = Modifier.height(4.dp))
            ContentCard(
                contentState = content,
                updateSentenceContent = updateSentenceContent,
                speakerClick = playAudio
            )
            if (index == contents.lastIndex) Spacer(modifier = Modifier.height(8.dp))
        }
    }
}