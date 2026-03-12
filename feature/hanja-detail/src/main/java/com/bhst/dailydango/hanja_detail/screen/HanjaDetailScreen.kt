package com.bhst.dailydango.hanja_detail.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.designsystem.component.HanjaCard
import com.bhst.dailydango.model.hanja.HanjaContent

@Composable
fun HanjaDetailScreen(
    hanjas: List<String> = emptyList(),
    viewModel: HanjaDetailViewModel = hiltViewModel()
) {
    val contents by viewModel.hanjaContents.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getHanjaContents(hanjas)
    }
    HanjaDetailContent(
        contents = contents
    )
}

@Composable
fun HanjaDetailContent(
    contents: List<HanjaContent> = emptyList()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(
            contents,
            key = { it.hanja }
        ) {
            HanjaCard(
                hanjaContent = it
            )
        }
    }
}