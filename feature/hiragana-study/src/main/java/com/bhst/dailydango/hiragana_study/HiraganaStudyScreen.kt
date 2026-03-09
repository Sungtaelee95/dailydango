package com.bhst.dailydango.hiragana_study

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.word_type.WordType
import com.bhst.dailydango.util.HiraganaData
import kotlinx.coroutines.launch

@Composable
fun HiraganaStudyScreen(
    navigateToHiraganaDetail: (WordType, String) -> Unit,
) {
    HiraganaStudyContent(
        navigateToHiraganaDetail = navigateToHiraganaDetail
    )
}


@Composable
fun HiraganaStudyContent(
    navigateToHiraganaDetail: (WordType, String) -> Unit = {_, _ ->},
) {
    val tabs = listOf("기본", "탁음/반탁음", "요음", "촉음")

    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 1. 탭 메뉴 영역
        Surface(
            modifier = Modifier.wrapContentSize(),
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    val interactionSource = remember { MutableInteractionSource() }
                    val isSelected = pagerState.currentPage == index
                    Text(
                        text = title,
                        color = if (isSelected) MaterialTheme.colorScheme.primaryFixed else MaterialTheme.colorScheme.onSurface,
                        style = if (isSelected) DailyDangoTheme.typography.bold20 else DailyDangoTheme.typography.light20,
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
        }


        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            // 페이지 인덱스(page)에 따라 맵핑
            val currentMap = when (page) {
                0 -> HiraganaData.basicMap
                1 -> HiraganaData.dakuonMap
                2 -> HiraganaData.yoonMap
                else -> HiraganaData.sokuonMap
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(currentMap.entries.toList()) { entry ->
                    KanaGridRow(
                        rowHeader = entry.key,
                        items = entry.value,
                        page = page,
                        onClick = navigateToHiraganaDetail
                    )
                }
            }
        }
    }
}

@Composable
fun KanaGridRow(
    rowHeader: String,
    items: List<String>,
    page: Int,
    onClick: (WordType, String) -> Unit = { _, _ -> }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                if (rowHeader.isNotEmpty() && rowHeader.isNotBlank()) {
                    when (page) {
                        0 -> onClick(WordType.BASIC, rowHeader)
                        1 -> onClick(WordType.DAKUON, rowHeader)
                        2 -> onClick(WordType.YOON, rowHeader)
                        else -> onClick(WordType.SOKUON, rowHeader)
                    }
                }
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 좌측 행(행 제목) 렌더링
        if (rowHeader.isEmpty() || rowHeader.isBlank()) {
            Spacer(modifier = Modifier.weight(1f)) // 빈칸 처리
        } else {
            KanaCell(text = rowHeader, isHeader = true, modifier = Modifier.weight(1f))
        }

        // 우측 열(아이템들) 렌더링
        items.forEach { char ->
            if (char.isEmpty()) {
                Spacer(modifier = Modifier.weight(1f)) // 빈칸 처리
            } else {
                // '열'이나 '~'가 포함된 경우 헤더(테두리) 디자인으로 렌더링
                val isHeaderItem = char.contains("열") || char.contains("~")
                KanaCell(text = char, isHeader = isHeaderItem, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun KanaCell(text: String, isHeader: Boolean, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(36.dp)
            .background(
                color = if (isHeader) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surfaceTint,
                shape = RoundedCornerShape(10.dp)
            )
            .then(
                if (isHeader) Modifier.border(
                    1.dp,
                    MaterialTheme.colorScheme.primaryFixed,
                    RoundedCornerShape(12.dp)
                )
                else Modifier
            )
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primaryFixed,
            style = DailyDangoTheme.typography.bold16,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HiraganaStudyContentPreview() {
    DailyDangoTheme {
        HiraganaStudyContent()
    }
}