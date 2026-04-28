package com.bhst.dailydango.designsystem.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

// 네비게이션 타입 정의 확장
enum class TopAppBarNavigationType {
    Home, // 메인 화면 (로고 왼쪽, 메뉴 오른쪽)
    Back, // 서브 화면 (뒤로가기 왼쪽, 로고 중앙)
    None,
}

@Composable
fun DailyDangoTopAppBar(
    @StringRes titleRes: Int = R.string.dailydango,
    @DrawableRes logoRes: Int? = R.drawable.top_bar_logo,
    navigationIconContentDescription: String? = null,
    modifier: Modifier = Modifier,
    navigationType: TopAppBarNavigationType = TopAppBarNavigationType.Home, // 기본값 변경
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    containerColor: Color = MaterialTheme.colorScheme.background,
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {}, // 우측 아이콘 클릭 동작,
    onSuggestionClick: () -> Unit = {}
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(containerColor)
                .then(modifier)
        ) {
            // 1. 좌측 요소 (Back 버튼 또는 Home 로고)
            if (navigationType == TopAppBarNavigationType.Back) {
                IconButton(
                    onClick = onNavigationClick,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = if (navigationType == TopAppBarNavigationType.Back) Icons.Filled.ArrowBackIosNew else Icons.Filled.Close,
                        contentDescription = navigationIconContentDescription,
                        tint = contentColor
                    )
                }
            } else if (navigationType == TopAppBarNavigationType.Home) {
                // Home 타입일 때는 왼쪽에 로고+타이틀 배치
                Row(
                    modifier = Modifier
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DailyDangoTitleContent(logoRes, titleRes, contentColor)
                }
            }

            if (navigationType == TopAppBarNavigationType.None) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DailyDangoTitleContent(logoRes, titleRes, contentColor)
                }
            }

            // 2. 중앙 요소 (Back 타입일 때만 중앙에 로고+타이틀 표시)
            if (navigationType == TopAppBarNavigationType.Back) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DailyDangoTitleContent(logoRes, titleRes, contentColor)
                }
            }

            // 3. 우측 요소 (Home 타입일 때 메뉴 아이콘)
            if (navigationType == TopAppBarNavigationType.Home) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = onSuggestionClick,
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.campaign_24px),
                            contentDescription = "Menu",
                            tint = contentColor
                        )
                    }
                    IconButton(
                        onClick = onActionClick,
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu, // 햄버거 메뉴 아이콘
                            contentDescription = "Menu",
                            tint = contentColor
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun DailyDangoTitleContent(
    @DrawableRes logoRes: Int?,
    @StringRes titleRes: Int,
    contentColor: Color
) {
    if (logoRes != null) {
        Image(
            painter = painterResource(id = logoRes),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp) // 로고 크기 조절
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
    Column {
        Text(
            text = stringResource(id = titleRes),
            style = DailyDangoTheme.typography.bold24,
            color = contentColor
        )
        Spacer(
            modifier = Modifier.height(4.dp)
        )
        Text(
            text = stringResource(id = R.string.daily_litle_japenes),
            style = DailyDangoTheme.typography.light16
        )
    }

}

// --- Previews ---

@Preview(showBackground = true)
@Composable
private fun DailyDangoTopAppBarNonePreview() {
    DailyDangoTheme {
        DailyDangoTopAppBar(
            navigationType = TopAppBarNavigationType.None,
            titleRes = R.string.dailydango,
            logoRes = R.drawable.top_bar_logo
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun DailyDangoTopAppBarHomePreview() {
    DailyDangoTheme {
        DailyDangoTopAppBar(
            navigationType = TopAppBarNavigationType.Home,
            titleRes = R.string.dailydango,
            logoRes = R.drawable.top_bar_logo
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
            titleRes = R.string.dailydango,
            logoRes = R.drawable.top_bar_logo
        )
    }
}