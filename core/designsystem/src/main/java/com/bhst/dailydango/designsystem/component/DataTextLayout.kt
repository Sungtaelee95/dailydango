package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun DataTextLayout(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = contentAlignment
        ) {
            content()
        }
    }
}

@Composable
@Preview
fun DataTextLayoutPreview() {
    DailyDangoTheme {
        DataTextLayout(
            modifier = Modifier
                .width(160.dp)
                .height(80.dp),
        ) {
            Text(text = "안녕 클레오파트라")
        }
    }
}