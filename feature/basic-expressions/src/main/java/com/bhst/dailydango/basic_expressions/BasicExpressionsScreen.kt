package com.bhst.dailydango.basic_expressions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BasicExpressionsScreen(

) {
    BasicExpressionsContent()
}

@Composable
fun BasicExpressionsContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "기초 표현 익히기"
        )
    }
}