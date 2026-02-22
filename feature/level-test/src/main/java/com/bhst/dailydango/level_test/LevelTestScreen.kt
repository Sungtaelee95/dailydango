package com.bhst.dailydango.level_test

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LevelTestScreen(

) {
    LevelTestContent()
}

@Composable
fun LevelTestContent(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "레벨 테스트"
        )
    }
}