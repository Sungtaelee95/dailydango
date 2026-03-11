package com.bhst.dailydango.katakana_detail

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.WordCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.word.WordContentState
import com.bhst.dailydango.model.word_type.WordType
import com.bhst.dailydango.util.KatakanaData

@Composable
fun KatakanaDetailScreen(
    wordType: WordType,
    rowHeader: String,
    viewModel: KatakanaDetailViewModel = hiltViewModel()
) {
    val selectedRow by viewModel.selectedRow.collectAsStateWithLifecycle()
    val wordState by viewModel.wordContentState.collectAsStateWithLifecycle()
    val currentMap = when (wordType) {
        WordType.BASIC -> KatakanaData.basicMap
        WordType.DAKUON -> KatakanaData.dakuonMap
        WordType.YOON -> KatakanaData.yoonMap
        WordType.SOKUON -> KatakanaData.sokuonMap
    }
    val tabList = currentMap.keys.filter { it.isNotEmpty() }
    val currentItems = currentMap[selectedRow]?.filter { it.isNotEmpty() } ?: emptyList()

    LaunchedEffect(Unit) {
        viewModel.updateSelectedRow(rowHeader)
    }
    LaunchedEffect(currentItems) {
        if (selectedRow.isNotEmpty()) {
            viewModel.updateCurrentItems(currentItems)
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopAudio()
        }
    }
    KatakanaDetailContent(
        tabList = tabList,
        selectedRow = selectedRow,
        wordState = wordState,
        onTabSelected = viewModel::updateSelectedRow, // 또는 { viewModel.updateSelectedRow(it) },
        playAudio = viewModel::playAudio,
        updateContent = viewModel::updateContent
    )
}

@Composable
fun KatakanaDetailContent(
    tabList: List<String> = emptyList(),
    selectedRow: String = "",
    wordState: List<WordContentState> = emptyList(),
    onTabSelected: (String) -> Unit = {},
    playAudio: (Uri?) -> Unit = {},
    updateContent: (WordContentState) -> Unit = {}
) {
    val listState = rememberLazyListState()

    LaunchedEffect(selectedRow) {
        val index = tabList.indexOf(selectedRow)
        if (index >= 0) {
            // 선택된 인덱스로 부드럽게 스크롤 (원한다면 scrollToItem을 사용하여 애니메이션 없이 이동할 수도 있습니다)
            listState.animateScrollToItem(index)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 1. 상단 가로 스크롤 탭 (LazyRow)
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(tabList) { tabName ->
                val isSelected = tabName == selectedRow
                val interactionSource = remember { MutableInteractionSource() }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.primaryFixed
                            else MaterialTheme.colorScheme.surface // 비활성화 탭 배경색
                        )
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { onTabSelected(tabName) }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = tabName,
                        color = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primaryFixed,
                        style = if (isSelected) DailyDangoTheme.typography.bold16 else DailyDangoTheme.typography.medium16
                    )
                }
            }
        }

        // 2. 선택된 행의 글자들을 보여주는 리스트 (LazyColumn)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(wordState) { item ->
                WordCard(
                    wordContentState = item,
                    speakerClick = playAudio,
                    updateContent = updateContent
                )
            }
        }
    }
}