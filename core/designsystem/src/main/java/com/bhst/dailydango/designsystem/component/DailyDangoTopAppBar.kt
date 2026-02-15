package com.bhst.dailydango.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun DailyDangoTopAppBar(
    @StringRes titleRes: Int,
    navigationIconContentDescription: String?,
    modifier: Modifier = Modifier,
    navigationType: TopAppBarNavigationType = TopAppBarNavigationType.Back,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    containerColor: Color = MaterialTheme.colorScheme.background,
    actionButtons: @Composable () -> Unit = {},
    onNavigationClick: () -> Unit = {},
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        val icon: @Composable (Modifier, imageVector: ImageVector) -> Unit =
            { modifier, imageVector ->
                IconButton(
                    onClick = onNavigationClick,
                    modifier = modifier.size(60.dp)
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(containerColor)
                .pointerInput(Unit) { /* no-op */ }
                .then(modifier)
        ) {
            if (navigationType == TopAppBarNavigationType.Back) {
                icon(
                    Modifier.align(Alignment.CenterStart),
                    Icons.Filled.ArrowBackIosNew,
                )
            }
            Row(Modifier.align(Alignment.CenterEnd)) {
                actionButtons()
                if (navigationType == TopAppBarNavigationType.Close) {
                    icon(
                        Modifier,
                        Icons.Filled.Close
                    )
                }
            }
            Text(
                text = stringResource(id = titleRes),
                style = DailyDangoTheme.typography.bold14,
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

enum class TopAppBarNavigationType { Back, Close }

@Preview
@Composable
private fun DailyDangoTopAppBarPreviewBack() {
    DailyDangoTopAppBar(
        titleRes = R.string.untitled,
        navigationType = TopAppBarNavigationType.Back,
        navigationIconContentDescription = "Navigation icon"
    )
}

@Preview
@Composable
private fun DailyDangoTopAppBarPreviewClose() {
    DailyDangoTopAppBar(
        titleRes = R.string.untitled,
        navigationType = TopAppBarNavigationType.Close,
        navigationIconContentDescription = "Navigation icon"
    )
}
