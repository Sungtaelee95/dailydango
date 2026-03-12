package com.bhst.dailydango.hiragana_katakana_tip.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.hiragana.katakana.tip.R
import com.bhst.dailydango.designsystem.component.DailyDangoElevationCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.tip.Tip
import kotlinx.coroutines.launch

@Composable
fun HiraganaKatakanaTipScreen(
    viewModel: HiraganaKatakanaTipViewModel = hiltViewModel(),
    navigateToHiraganaStudy: () -> Unit = {},
    navigateToKatakanaStudy: () -> Unit = {}
) {
    val hiraganaTips by viewModel.hiraganaTips.collectAsStateWithLifecycle()
    val katakanaTips by viewModel.katakanaTips.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getTips()
    }

    HiraganaKatakanaTipContent(
        hiraganaTips = hiraganaTips,
        katakanaTips = katakanaTips,
        navigateToHiraganaStudy = navigateToHiraganaStudy,
        navigateToKatakanaStudy = navigateToKatakanaStudy
    )
}

@Composable
fun HiraganaKatakanaTipContent(
    hiraganaTips: List<Tip> = emptyList(),
    katakanaTips: List<Tip> = emptyList(),
    navigateToHiraganaStudy: () -> Unit = {},
    navigateToKatakanaStudy: () -> Unit = {}
) {
    val pageState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(
        stringResource(R.string.hiragana),
        stringResource(R.string.katakana)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(color = MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = pageState.currentPage == index
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = title,
                    style = if (isSelected) DailyDangoTheme.typography.bold16 else DailyDangoTheme.typography.light16,
                    color = if (isSelected) MaterialTheme.colorScheme.primaryFixed else MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        coroutineScope.launch {
                            pageState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            when (page) {
                0 -> TipsScreen(tips = hiraganaTips, navigateTo = navigateToHiraganaStudy)
                1 -> TipsScreen(tips = katakanaTips, navigateTo = navigateToKatakanaStudy)
            }
        }
    }
}

@Composable
fun TipsScreen(
    tips: List<Tip> = emptyList(),
    navigateTo: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(tips) { tip ->
                DailyDangoElevationCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = 20.dp,
                    elevation = 4.dp,
                    contentColor = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = tip.title,
                            style = DailyDangoTheme.typography.bold16,
                            color = MaterialTheme.colorScheme.primaryFixed
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = tip.content,
                            style = DailyDangoTheme.typography.medium14,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
        Button(
            onClick = navigateTo,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = stringResource(R.string.study_start),
                style = DailyDangoTheme.typography.bold20,
                color = MaterialTheme.colorScheme.inverseSurface
            )
        }
    }
}