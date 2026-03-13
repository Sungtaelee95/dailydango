package com.bhst.dailydango.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.search.R
import com.bhst.dailydango.designsystem.component.DailyDangoElevationCard
import com.bhst.dailydango.designsystem.component.FavoriteContentCard
import com.bhst.dailydango.designsystem.component.NotOutLineSearchField
import com.bhst.dailydango.designsystem.component.SearchContentCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.content.ContentState

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val contents by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getAllContent()
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.soundPlayRelease()
        }
    }
    SearchContent(
        contents = contents,
        updateContent = viewModel::updateSentenceContent,
        playSoundFor = viewModel::playSoundFor
    )
}

@Composable
fun SearchContent(
    contents: List<ContentState> = emptyList(),
    updateContent: (ContentState) -> Unit = {},
    playSoundFor: (String) -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    val filterContents = contents.filter { content ->
        content.japaneseTitle.contains(searchText) ||
                content.titleToKorean.contains(searchText) ||
                content.titleHanja.contains(searchText) ||
                content.japaneseTitleOfSoundToKorea.contains(searchText)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        DailyDangoElevationCard(
            modifier = Modifier
                .fillMaxWidth(),
            shape = 24.dp,
            elevation = 8.dp,
            enabled = false
        ) {
            NotOutLineSearchField(
                searchText = searchText,
                onValueChange = { searchText = it },
                hint = R.string.hint_search_word_or_grammar,
                enabled = true
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(filterContents) { index, content ->
                if (index == 0) Spacer(modifier = Modifier.height(4.dp))
                SearchContentCard(
                    contentState = content,
                    updateContent = updateContent,
                    speakerClick = { playSoundFor(content.titleHanja.ifEmpty { content.japaneseTitle }) },
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
                if (index == filterContents.lastIndex) Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchContentPreview() {
    DailyDangoTheme {
        SearchContent()
    }
}