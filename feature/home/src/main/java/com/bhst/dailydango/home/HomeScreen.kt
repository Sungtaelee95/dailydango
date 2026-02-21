package com.bhst.dailydango.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    navigateToHiraganaStudy: () -> Unit = {},
    navigateToKatakanaStudy: () -> Unit = {},
    navigateToGrammarStudy: () -> Unit = {},
    navigateToGrammarTest: () -> Unit = {},
) {
    HomeContent(
        navigateToHiraganaStudy = navigateToHiraganaStudy,
        navigateToKatakanaStudy = navigateToKatakanaStudy,
        navigateToGrammarStudy = navigateToGrammarStudy,
        navigateToGrammarTest = navigateToGrammarTest,
    )
}

@Composable
fun HomeContent(
    navigateToHiraganaStudy: () -> Unit = {},
    navigateToKatakanaStudy: () -> Unit = {},
    navigateToGrammarStudy: () -> Unit = {},
    navigateToGrammarTest: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = navigateToHiraganaStudy
        ) {
            Text(text = "히라가나 학습하러 가기")
        }
    }
}