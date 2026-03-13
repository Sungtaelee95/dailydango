package com.bhst.dailydango.favorite

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.menu.R
import com.bhst.dailydango.designsystem.component.ColorBar
import com.bhst.dailydango.designsystem.component.FavoriteContentCard
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.content.ContentState

@Composable
fun FavoriteContentScreen(
    navigateToHanjaDetail: (List<String>) -> Unit = {},
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val contents by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getFavoriteContents()
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.soundPlayRelease()
        }
    }
    FavoriteContentContent(
        contents = contents,
        updateFavoriteContent = viewModel::updateFavoriteContent,
        playAudio = viewModel::soundPlayForContent,
        navigateToHanjaDetail = navigateToHanjaDetail
    )
}

@Composable
fun FavoriteContentContent(
    contents: List<ContentState> = emptyList(),
    updateFavoriteContent: (ContentState) -> Unit = {},
    playAudio: (Uri?) -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit = {}
) {
    Column {
        ColorBar(
            color = MaterialTheme.colorScheme.onPrimaryFixed
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                ImageCard(
                    painter = painterResource(R.drawable.favorites_img),
                    contentDescription = "Favorites",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(R.string.favorites),
                    style = DailyDangoTheme.typography.bold20,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 28.dp, end = 28.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(
                contents,
                key = { _, content -> content.japaneseTitle }) { index, content ->
                if (index == 0) Spacer(modifier = Modifier.height(4.dp))
                FavoriteContentCard(
                    contentState = content,
                    updateContent = updateFavoriteContent,
                    speakerClick = playAudio,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
                if (index == contents.lastIndex) Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}