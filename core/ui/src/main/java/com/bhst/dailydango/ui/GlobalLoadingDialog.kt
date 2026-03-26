package com.bhst.dailydango.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun GlobalLoadingDialog(
    loadingDialogManager: LoadingDialogManager = LoadingDialogManager()
) {
    val isLoading by loadingDialogManager.isLoading.collectAsState(initial = false)
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    enabled = false,
                    onClick = { /* 아무 동작 없음 */ }
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "잠시만 기다려주세요.",
                    style = DailyDangoTheme.typography.medium16,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GlobalLoadingDialogPreview() {
    DailyDangoTheme {
        GlobalLoadingDialog()
    }
}