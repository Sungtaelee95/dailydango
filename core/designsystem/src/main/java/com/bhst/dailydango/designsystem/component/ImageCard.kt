package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    filter: ColorFilter? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                onClick = onClick,
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null
            ),
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = filter
        )
    }
}
