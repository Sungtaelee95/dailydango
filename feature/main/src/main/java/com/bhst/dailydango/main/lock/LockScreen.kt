package com.bhst.dailydango.main.lock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.feature.main.R
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun LockScreen(
    message: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ImageCard(
            painter = painterResource(R.drawable.lock_img),
            contentDescription = stringResource(R.string.app_is_lock),
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = message,
            style = DailyDangoTheme.typography.bold16,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }

}