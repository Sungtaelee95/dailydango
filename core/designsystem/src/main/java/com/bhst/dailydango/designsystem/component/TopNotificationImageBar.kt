package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.somuna.app.core.designsystem.R

@Composable
fun TopNotificationImageBar(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    onNotificationClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(end = 8.dp),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageCard(
                modifier = Modifier
                    .width(145.dp)
                    .height(58.dp),
                painter = if (isDarkTheme) {
                    painterResource(R.drawable.moca_home_logo_dark)
                } else {
                    painterResource(R.drawable.moca_home_logo_light)
                },
                contentDescription = stringResource(R.string.moca_logo)
            )
        }
        Image(
            painter = painterResource(R.drawable.notification),
            contentDescription = stringResource(R.string.notification),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
                .clickable(onClick = {
                    onNotificationClick()
                }),
        )
    }

}

@Composable
@Preview(showBackground = true)
fun TopImageBarPreview() {
    TopNotificationImageBar(isDarkTheme = false)
}

@Composable
@Preview()
fun TopImageBarDarkPreview() {
    TopNotificationImageBar(isDarkTheme = true)
}