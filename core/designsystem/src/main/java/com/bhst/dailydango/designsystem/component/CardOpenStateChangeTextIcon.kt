package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun CardOpenStateChangeTextIcon(
    openState: Boolean,
    onOpenStateChange: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        if (openState) {
            Text(
                text = stringResource(R.string.all_close),
                style = DailyDangoTheme.typography.medium16,
                modifier = Modifier
                    .clickable(
                        onClick = onOpenStateChange
                    )
            )
            ImageCard(
                painter = painterResource(R.drawable.keyboard_arrow_up_24px),
                onClick = onOpenStateChange,
                contentDescription = stringResource(R.string.all_close),
                modifier = Modifier.size(20.dp),
                filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        } else {
            Text(
                text = stringResource(R.string.all_open),
                style = DailyDangoTheme.typography.medium16,
                modifier = Modifier
                    .clickable(
                        onClick = onOpenStateChange
                    )
            )
            ImageCard(
                painter = painterResource(R.drawable.keyboard_arrow_down_24px),
                onClick = onOpenStateChange,
                contentDescription = stringResource(R.string.all_open),
                modifier = Modifier.size(20.dp),
                filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}