package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColorBar(
    color: Color,
    content: @Composable () -> Unit,
) {
    Surface(
        color = color,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        shadowElevation = 4.dp

    ) {
        content()
    }
}

@Composable
@Preview
fun ColorBarPreview() {
    ColorBar(
        color = Color.Red,
    ) {
        Text("하이")
    }
}