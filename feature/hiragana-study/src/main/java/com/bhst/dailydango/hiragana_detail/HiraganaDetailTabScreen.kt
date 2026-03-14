package com.bhst.dailydango.hiragana_detail

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.WordTabCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.word.WordContentState
import com.bhst.dailydango.model.word_type.WordType
import com.bhst.dailydango.util.HiraganaData

@Composable
fun HiraganaDetailTabScreen(
    wordType: WordType,
    rowHeader: String,
    viewModel: HiraganaDetailViewModel = hiltViewModel()
) {
    val selectedRow by viewModel.selectedRow.collectAsStateWithLifecycle()
    val wordState by viewModel.wordContentState.collectAsStateWithLifecycle()
    val currentMap = when (wordType) {
        WordType.BASIC -> HiraganaData.basicMap
        WordType.DAKUON -> HiraganaData.dakuonMap
        WordType.YOON -> HiraganaData.yoonMap
        WordType.SOKUON -> HiraganaData.sokuonMap
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
    HiraganaDetailTabContent(
        tabList = tabList,
        selectedRow = selectedRow,
        wordState = wordState,
        onTabSelected = viewModel::updateSelectedRow, // 또는 { viewModel.updateSelectedRow(it) },
        playAudio = viewModel::playAudio,
        updateContent = viewModel::updateContent
    )
}

@Composable
fun HiraganaDetailTabContent(
    tabList: List<String> = emptyList(),
    selectedRow: String = "",
    wordState: List<WordContentState> = emptyList(),
    onTabSelected: (String) -> Unit = {},
    playAudio: (Uri?) -> Unit = {},
    updateContent: (WordContentState) -> Unit = {}
) {
    val listState = rememberLazyListState()
    val scrollState = rememberScrollState()

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
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. 상단 가로 스크롤 탭 (LazyRow)
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(tabList) { tabName ->
                val isSelected = tabName == selectedRow
                val interactionSource = remember { MutableInteractionSource() }
                Spacer(modifier = Modifier.width(16.dp))
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
                        color = if (isSelected) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.primaryFixed,
                        style = if (isSelected) DailyDangoTheme.typography.bold20 else DailyDangoTheme.typography.medium20
                    )
                }
            }
        }

        // 2. 선택된 행의 글자들을 보여주는 리스트 (LazyColumn)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(480.dp)
                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // itemsIndexed 대신 일반 forEachIndexed 사용
            wordState.forEachIndexed { index, item ->
                if (index == 0) {
                    Spacer(modifier = Modifier.height(4.dp))
                }
                WordTabCard(
                    wordContentState = item,
                    speakerClick = playAudio,
                    updateContent = updateContent
                )
                if (index == wordState.lastIndex) {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = TABLET)
fun HiraganaDetailTabContentPreview() {
    DailyDangoTheme {
        HiraganaDetailTabContent()
    }
}