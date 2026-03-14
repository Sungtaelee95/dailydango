package com.bhst.dailydango.favorite

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.menu.R
import com.bhst.dailydango.designsystem.component.ColorBar
import com.bhst.dailydango.designsystem.component.FavoriteContentTabCard
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.content.ContentState

@Composable
fun FavoriteContentTabScreen(
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
    FavoriteContentTabContent(
        contents = contents,
        updateFavoriteContent = viewModel::updateFavoriteContent,
        playAudio = viewModel::soundPlayForContent,
        navigateToHanjaDetail = navigateToHanjaDetail
    )
}

@Composable
fun FavoriteContentTabContent(
    contents: List<ContentState> = emptyList(),
    updateFavoriteContent: (ContentState) -> Unit = {},
    playAudio: (Uri?) -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorBar(
            color = MaterialTheme.colorScheme.onPrimaryFixed,
            height = 60
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
                        .size(60.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(R.string.favorites),
                    style = DailyDangoTheme.typography.bold24,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
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
            itemsIndexed(
                contents
            ) { index, content ->
                FavoriteContentTabCard(
                    contentState = content,
                    updateContent = updateFavoriteContent,
                    speakerClick = playAudio,
                    navigateToHanjaDetail = navigateToHanjaDetail,
                    modifier = Modifier.width(480.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = TABLET)
@Composable
fun FavoriteContentTabContentPreview() {
    DailyDangoTheme {
        FavoriteContentTabContent()
    }
}