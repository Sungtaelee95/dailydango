package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoColor
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun TextChip(
    text: String,
    containerColor: Color,
    labelColor: Color,
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    style: TextStyle = MaterialTheme.typography.bodyLarge

) {
    Surface(
        shape = DailyDangoTheme.shape.chip,
        color = containerColor,
        contentColor = labelColor,
        border = border,
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .defaultMinSize(minHeight = 20.dp)
                .padding(horizontal = 12.dp, vertical = 2.dp),
        ) {
            Text(
                text = text,
                style = style,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextChipPreview() {
    MaterialTheme {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            TextChip(
                "카테고리",
                containerColor = Color.Transparent,
                labelColor = DailyDangoColor.Black,
                border = BorderStroke(1.dp, DailyDangoColor.Black),
            )
            TextChip(
                "Track 01",
                containerColor = DailyDangoColor.Black,
                labelColor = DailyDangoColor.White,
            )
            TextChip(
                "16:45 발표",
                containerColor = DailyDangoColor.Black,
                labelColor = DailyDangoColor.Black,
            )
        }
    }
}
