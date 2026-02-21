package com.bhst.dailydango.search.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun SearchScreen(

) {
    SearchContent()
}

@Composable
fun SearchContent(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "search Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun SearchContentPreview() {
    DailyDangoTheme {
        SearchContent()
    }
}