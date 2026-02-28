package com.bhst.dailydango.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GlobalLoadingDialog(
    loadingDialogManager: LoadingDialogManager
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
            CircularProgressIndicator()
        }
    }
}