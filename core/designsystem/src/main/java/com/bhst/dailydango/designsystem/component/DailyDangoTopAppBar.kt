package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

// 네비게이션 타입 정의 확장
enum class TopAppBarNavigationType {
    Back,
    None,
}

@Composable
fun DailyDangoTopAppBar(
    title: String = stringResource(R.string.dailydango),
    navigationIconContentDescription: String? = null,
    modifier: Modifier = Modifier,
    navigationType: TopAppBarNavigationType = TopAppBarNavigationType.None, // 기본값 변경
    contentColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.background,
    onNavigationClick: () -> Unit = {},
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .defaultMinSize(minHeight = 48.dp)
                .background(containerColor)
                .then(modifier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. 좌측 요소 (Back 버튼 또는 Home 로고)
            if (navigationType == TopAppBarNavigationType.Back) {
                IconButton(
                    onClick = onNavigationClick,
                ) {
                    Icon(
                        imageVector = if (navigationType == TopAppBarNavigationType.Back) Icons.Filled.ArrowBackIosNew else Icons.Filled.Close,
                        contentDescription = navigationIconContentDescription,
                        tint = contentColor
                    )
                }
            }
            when (navigationType) {
                TopAppBarNavigationType.None -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        DailyDangoTitleContent(title, contentColor)
                    }
                }

                TopAppBarNavigationType.Back -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DailyDangoTitleContent(title, contentColor)
                    }
                }
            }
        }
    }
}


@Composable
private fun DailyDangoTitleContent(
    title: String,
    contentColor: Color
) {
    Text(
        text = title,
        style = DailyDangoTheme.typography.medium18,
        color = contentColor
    )
}

// --- Previews ---

@Preview(showBackground = true)
@Composable
private fun DailyDangoTopAppBarNonePreview() {
    DailyDangoTheme {
        DailyDangoTopAppBar(
            navigationType = TopAppBarNavigationType.None,
            title = stringResource(R.string.dailydango),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun DailyDangoTopAppBarBackPreview() {
    DailyDangoTheme {
        DailyDangoTopAppBar(
            navigationType = TopAppBarNavigationType.Back,
            navigationIconContentDescription = "Back",
            title = stringResource(R.string.dailydango),
        )
    }
}