package com.bhst.dailydango.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.feature.menu.R
import com.bhst.dailydango.designsystem.component.MenuCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun MenuTabScreen(
    navigateToFavorite: () -> Unit,
    navigateToTheme: () -> Unit,
    navigateToPlaySpeed: () -> Unit,
    navigateToOss: () -> Unit
) {
    MenuTabContent(
        navigateToFavorite = navigateToFavorite,
        navigateToTheme = navigateToTheme,
        navigateToPlaySpeed = navigateToPlaySpeed,
        navigateToOss = navigateToOss
    )
}

@Composable
fun MenuTabContent(
    navigateToFavorite: () -> Unit = {},
    navigateToTheme: () -> Unit = {},
    navigateToPlaySpeed: () -> Unit = {},
    navigateToOss: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(480.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuCard(
                title = stringResource(R.string.favorites),
                subTitle = stringResource(R.string.view_saved_words),
                img = R.drawable.favorites_img,
                onClick = navigateToFavorite
            )

//            MenuCard(
//                title = stringResource(R.string.statistics),
//                subTitle = stringResource(R.string.my_learning_history),
//                img = R.drawable.statistics_img,
//                onClick = {}
//            )

            MenuCard(
                title = stringResource(R.string.theme),
                subTitle = stringResource(R.string.change_theme),
                img = R.drawable.theme_img,
                onClick = navigateToTheme
            )

            MenuCard(
                title = stringResource(R.string.speed_control),
                subTitle = stringResource(R.string.custom_speed_control),
                img = R.drawable.speed_img,
                onClick = navigateToPlaySpeed
            )

            Text(
                text = stringResource(R.string.oss_license),
                style = DailyDangoTheme.typography.medium16,
                modifier = Modifier
                    .clickable(
                        onClick = navigateToOss
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = TABLET)
fun MenuTabContentPreview() {
    DailyDangoTheme {
        MenuTabContent()
    }
}