package com.bhst.dailydango.hiragana_study

import android.R.attr.text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HiraganaStudyScreen(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Hiragana Study Screen"
        )
    }
}