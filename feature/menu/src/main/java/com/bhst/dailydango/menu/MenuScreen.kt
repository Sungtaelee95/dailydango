package com.bhst.dailydango.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.feature.menu.R
import com.bhst.dailydango.designsystem.component.MenuCard

@Composable
fun MenuScreen(
    navigateToFavorite: () -> Unit,
    navigateToTheme: () -> Unit,
    navigateToPlaySpeed: () -> Unit
) {
    MenuContent(
        navigateToFavorite = navigateToFavorite,
        navigateToTheme = navigateToTheme,
        navigateToPlaySpeed= navigateToPlaySpeed
    )
}

@Composable
fun MenuContent(
    navigateToFavorite: () -> Unit,
    navigateToTheme: () -> Unit,
    navigateToPlaySpeed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        MenuCard(
            title = stringResource(R.string.favorites),
            subTitle = stringResource(R.string.view_saved_words),
            img = R.drawable.favorites_img,
            onClick = navigateToFavorite
        )

//        MenuCard(
//            title = stringResource(R.string.statistics),
//            subTitle = stringResource(R.string.my_learning_history),
//            img = R.drawable.statistics_img,
//            onClick = {}
//        )

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

    }
}

