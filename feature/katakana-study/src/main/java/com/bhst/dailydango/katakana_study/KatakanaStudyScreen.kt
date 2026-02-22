package com.bhst.dailydango.katakana_study

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun KatakanaStudyScreen(

) {
    KatakanaStudyContent()
}

@Composable
fun KatakanaStudyContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "KatakanaStudyScreen"
        )
    }
}