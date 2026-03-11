package com.bhst.dailydango.designsystem.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.word.WordContentState

@Composable
fun WordCard(
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
            WordCardTop(
                wordContentState = wordContentState,
                speakerClick = speakerClick,
            )
            WordCardMid(
                contentState = wordContentState,
                isOpenChanged = updateContent
            )
            if (wordContentState.isOpen) {
                WordCardBottom(
                    wordContentState = wordContentState,
                )
            }
        }
    }
}

@Composable
fun WordCardBottom(
    wordContentState: WordContentState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
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
        )
        SubcomposeAsyncImage(
            model = wordContentState.wordSoundUri,
            contentDescription = "Image",
            modifier = Modifier.size(240.dp),
            loading = {
                // 이미지를 로딩하는 동안 보여줄 화면
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            },
            error = {
                // 로딩 실패 또는 model(uri)이 null일 때 보여줄 화면(Fallback 역할 포함)
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        )
    }
}

@Composable
fun WordCardMid(
    contentState: WordContentState,
    isOpenChanged: (WordContentState) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.End
    ) {
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
    }
}

@Composable
fun WordCardTop(
    wordContentState: WordContentState,
    speakerClick: (Uri?) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
        verticalAlignment = Alignment.Top
    ) {
        ImageCard(
            painter = if (wordContentState.wordSoundUri == null) painterResource(R.drawable.volume_off_24px) else {
                painterResource(R.drawable.speaker_24px)
            },
            contentDescription = "Speaker",
            modifier = Modifier
                .size(28.dp),
            onClick = { speakerClick(wordContentState.wordSoundUri) },
            filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
        )
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