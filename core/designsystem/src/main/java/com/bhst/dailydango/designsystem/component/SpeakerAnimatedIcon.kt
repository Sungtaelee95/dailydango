package com.bhst.dailydango.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R

@Composable
fun SpeakerAnimatedIcon(
    visible: Boolean,
    onClick: () -> Unit,
    size: Int = 24
) {
    // 이제 RowScope 밖에 있으므로 에러가 발생하지 않습니다.
    Box(
        modifier = Modifier.size(size.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ImageCard(
                painter = painterResource(R.drawable.speaker_24px),
                contentDescription = "Speaker",
                modifier = Modifier.fillMaxSize(), // Box를 꽉 채우도록 수정
                onClick = onClick,
                filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}