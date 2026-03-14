package com.bhst.dailydango.hanja_detail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.HanjaCard
import com.bhst.dailydango.designsystem.component.HanjaTabCard
import com.bhst.dailydango.model.hanja.HanjaContent

@Composable
fun HanjaDetailTabScreen(
    hanjas: List<String> = emptyList(),
    viewModel: HanjaDetailViewModel = hiltViewModel()
) {
    val contents by viewModel.hanjaContents.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getHanjaContents(hanjas)
    }
    HanjaDetailTabContent(
        contents = contents
    )
}

@Composable
fun HanjaDetailTabContent(
    contents: List<HanjaContent> = emptyList()
) {
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
        items(
            contents,
        ) {
            HanjaTabCard(
                hanjaContent = it,
                modifier = Modifier.width(480.dp)
            )
        }
    }
}