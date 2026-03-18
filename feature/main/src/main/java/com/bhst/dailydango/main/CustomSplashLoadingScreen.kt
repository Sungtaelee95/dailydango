package com.bhst.dailydango.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.feature.main.R
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme


@Composable
fun CustomSplashLoadingScreen(progress: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 앱 로고나 아이콘을 여기에 추가할 수 있습니다.
        ImageCard(
            painter = painterResource(R.drawable.splash_icon),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.size(280.dp)
        )
        Text(
            text = stringResource(R.string.app_name),
            style = DailyDangoTheme.typography.bold24,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 퍼센트 텍스트 (예: 45%)
        Text(
            text = stringResource(R.string.loading_data) + " ${(progress * 100).toInt()}%",
            style = DailyDangoTheme.typography.light12,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 진행률 바
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(12.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CustomSplashLoadingScreenPreview() {
    DailyDangoTheme {
        CustomSplashLoadingScreen(progress = 0.4f)
    }
}