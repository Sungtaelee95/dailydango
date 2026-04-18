package com.bhst.dailydango.conversation.conversation

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.bhst.dailydango.app.feature.conversation.R
import com.bhst.dailydango.designsystem.component.CardOpenStateChangeTextIcon
import com.bhst.dailydango.designsystem.component.ColorBar
import com.bhst.dailydango.designsystem.component.ConversationContentCard
import com.bhst.dailydango.designsystem.component.ConversationContentTabCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.conversation.ConversationContentState

@Composable
fun ConversationTabScreen(
    chapter: Int,
    navigateToHanjaDetail: (List<String>) -> Unit = {},
    viewModel: ConversationViewModel = hiltViewModel()
) {
    val contents by viewModel.uiState.collectAsStateWithLifecycle()
    val isStateAllOpen by viewModel.isStateAllOpen.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getConversationContent(chapter)
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.soundPlayRelease()
        }
    }

    ConversationabTabContent(
        contents = contents,
        updateConversationContent = viewModel::updateConversationContent,
        playAudio = viewModel::soundPlayForContent,
        allPlayAudio = viewModel::soundPlayForContents,
        allOpenState = isStateAllOpen,
        changeOpenState = viewModel::changeOpenState,
        navigateToHanjaDetail = navigateToHanjaDetail,
        chapter = chapter
    )
}

@Composable
fun ConversationabTabContent(
    contents: List<ConversationContentState> = emptyList(),
    updateConversationContent: (ConversationContentState) -> Unit = {},
    playAudio: (Uri?) -> Unit = {},
    allOpenState: Boolean = false,
    allPlayAudio: (List<Uri>) -> Unit = {},
    changeOpenState: () -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit = {},
    chapter: Int = 1
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorBar(
            color = MaterialTheme.colorScheme.onPrimaryFixedVariant
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$chapter" + stringResource(R.string.chapter_conversation_study),
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

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .width(800.dp)
                .padding(start = 28.dp, end = 28.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(
                items = contents,
                key = { _, content -> content.id }
            ) { index, content ->
                if (index == 0) Spacer(modifier = Modifier.height(4.dp))
                ConversationContentTabCard(
                    contentState = content,
                    updateContent = updateConversationContent,
                    speakerClick = playAudio,
                    allSpeakClick = allPlayAudio,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
                if (index == contents.lastIndex) Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = TABLET)
fun SentenceStudyTabScreenPreview() {
    DailyDangoTheme {
        ConversationabTabContent()
    }
}