package com.bhst.dailydango.designsystem.component

import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.word.WordContentState

@Composable
fun WordTabCard(
    wordContentState: WordContentState,
    speakerClick: (Uri?) -> Unit = {},
    updateContent: (WordContentState) -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            WordTabCardTop(
                wordContentState = wordContentState,
                speakerClick = speakerClick,
            )
            WordTabCardMid(
                contentState = wordContentState,
                isOpenChanged = updateContent
            )
            if (wordContentState.isOpen) {
                WordTabCardBottom(
                    wordContentState = wordContentState,
                )
            }
        }
    }
}

@Composable
fun WordTabCardBottom(
    wordContentState: WordContentState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryFixedDim)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (wordContentState.tip.isNotEmpty()) {
            Text(
                text = stringResource(R.string.tip),
                style = DailyDangoTheme.typography.bold20,
                color = MaterialTheme.colorScheme.surfaceContainer,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
            )
            Text(
                text = wordContentState.tip,
                style = DailyDangoTheme.typography.medium16,
                color = MaterialTheme.colorScheme.surfaceContainer,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (wordContentState.writeGifUri != null) {
            Text(
                text = stringResource(R.string.writing_detail),
                style = DailyDangoTheme.typography.bold20,
                color = MaterialTheme.colorScheme.surfaceContainer,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
            )
            SubcomposeAsyncImage(
                model = wordContentState.writeGifUri,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                loading = {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                        Log.d("lstlst", "gif 오류")
                    }
                }
            )
        }
    }
}

@Composable
fun WordTabCardMid(
    contentState: WordContentState,
    isOpenChanged: (WordContentState) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        if (contentState.tip.isNotEmpty() || contentState.writeGifUri != null) {
            ImageCard(
                painter = if (contentState.isOpen) {
                    painterResource(R.drawable.keyboard_arrow_up_24px)
                } else {
                    painterResource(R.drawable.keyboard_arrow_down_24px)
                },
                modifier = Modifier
                    .size(28.dp),
                contentDescription = "Arrow",
                onClick = { isOpenChanged(contentState.copy(isOpen = !contentState.isOpen)) },
                filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        } else {
            Box(
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun WordTabCardTop(
    wordContentState: WordContentState,
    speakerClick: (Uri?) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier.size(28.dp),
            contentAlignment = Alignment.Center
        ) {
            SpeakerAnimatedIcon(
                visible = wordContentState.wordSoundUri != null,
                onClick = { speakerClick(wordContentState.wordSoundUri) },
                size = 28
            )
        }
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = wordContentState.word,
                style = DailyDangoTheme.typography.bold20,
                color = MaterialTheme.colorScheme.primaryFixed
            )
            Text(
                text = wordContentState.wordSound,
                style = DailyDangoTheme.typography.medium16,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}