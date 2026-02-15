package com.bhst.dailydango.designsystem.component

import androidx.appcompat.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoColor
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun IconTextChip(
    text: String,
    containerColor: Color,
    labelColor: Color,
    iconPainter: Painter,
    iconTint: Color,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = DailyDangoTheme.shape.chip,
        color = containerColor,
        contentColor = labelColor,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .defaultMinSize(minHeight = 20.dp)
                .padding(end = 10.dp)
        ) {
            Icon(
                painter = iconPainter,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier
                    .size(18.dp)
            )

            Text(
                text = text,
                style = DailyDangoTheme.typography.bold14,
            )
        }
    }
}

@Preview
@Composable
private fun IconTextChipPreview() {
    MaterialTheme {
        IconTextChip(
            "북마크",
            containerColor = DailyDangoColor.DarkGray,
            labelColor = DailyDangoColor.LightGray,
            iconPainter = painterResource(id = R.drawable.abc_btn_radio_material),
            iconTint = Color.Green,
        )
    }
}
