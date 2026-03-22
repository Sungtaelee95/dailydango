package com.bhst.dailydango.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.search.R
import com.bhst.dailydango.designsystem.component.DailyDangoElevationCard
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.component.NotOutLineSearchField
import com.bhst.dailydango.designsystem.component.SearchContentCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.content.ContentState

@Composable
fun SearchScreen(
    content: List<ContentState> = emptyList(),
    navigateToHanjaDetail: (List<String>) -> Unit = {},
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val contents by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.setSearchContent(content)
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.soundPlayRelease()
        }
    }
    SearchContent(
        contents = contents,
        updateContent = viewModel::updateSearchContent,
        favoriteClick = viewModel::updateSearchFavorite,
        navigateToHanjaDetail = navigateToHanjaDetail,
        playSoundFor = viewModel::playSoundFor
    )
}

@Composable
fun SearchContent(
    contents: List<ContentState> = emptyList(),
    updateContent: (ContentState) -> Unit = {},
    favoriteClick: (ContentState) -> Unit = {},
    playSoundFor: (String?) -> Unit = {},
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
        if (filterContents.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageCard(
                    modifier = Modifier
                        .size(240.dp),
                    painter = painterResource(R.drawable.not_search_img),
                    contentDescription = "not search"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.not_search_result),
                    style = DailyDangoTheme.typography.light20
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp)
            ) {
                itemsIndexed(
                    items = filterContents,
                    key = { _, content -> content.id }
                ) { index, content ->
                    SearchContentCard(
                        contentState = content,
                        updateContent = updateContent,
                        favoriteClick = favoriteClick,
                        speakerClick = { name -> playSoundFor(name) },
                        navigateToHanjaDetail = navigateToHanjaDetail
                    )
                }
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