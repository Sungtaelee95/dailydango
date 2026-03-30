package com.bhst.dailydango.basic_expressions.sentnece_study

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.basic.expressions.R
import com.bhst.dailydango.designsystem.component.CardOpenStateChangeTextIcon
import com.bhst.dailydango.designsystem.component.ColorBar
import com.bhst.dailydango.designsystem.component.ContentTabCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.content.ContentState

@Composable
fun SentenceStudyTabScreen(
    chapter: Int,
    navigateToHanjaDetail: (List<String>) -> Unit = {},
    viewModel: SentenceStudyViewModel = hiltViewModel()
) {
    val contents by viewModel.uiState.collectAsStateWithLifecycle()
    val isStateAllOpen by viewModel.isStateAllOpen.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getSentenceContent(chapter)
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.soundPlayRelease()
        }
    }
    SentenceStudyTabContent(
        contents = contents,
        updateSentenceContent = viewModel::updateSentenceContent,
        playAudio = viewModel::soundPlayForContent,
        navigateToHanjaDetail = navigateToHanjaDetail,
        allOpenState = isStateAllOpen,
        changeOpenState = viewModel::changeOpenState,
        chapter = chapter
    )
}

@Composable
fun SentenceStudyTabContent(
    contents: List<ContentState> = emptyList(),
    updateSentenceContent: (ContentState) -> Unit = {},
    playAudio: (Uri?) -> Unit = {},
    allOpenState: Boolean = false,
    changeOpenState: () -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit = {},
    chapter: Int = 1
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorBar(
            color = MaterialTheme.colorScheme.secondaryFixedDim
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$chapter" + stringResource(R.string.chapter_sentence_study),
                    style = DailyDangoTheme.typography.bold24,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        CardOpenStateChangeTextIcon(
            openState = allOpenState,
            onOpenStateChange = changeOpenState
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            modifier = Modifier
                .fillMaxHeight()
                .width(960.dp)
                .padding(start = 28.dp, end = 28.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(
                items = contents,
                key = {_, content -> content.id}
            ) { index, content ->
                ContentTabCard(
                    contentState = content,
                    updateContent = updateSentenceContent,
                    speakerClick = playAudio,
                    navigateToHanjaDetail = navigateToHanjaDetail,
                    modifier = Modifier.width(480.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = TABLET)
fun SentenceStudyTabScreenPreview() {
    DailyDangoTheme {
        SentenceStudyTabContent()
    }
}