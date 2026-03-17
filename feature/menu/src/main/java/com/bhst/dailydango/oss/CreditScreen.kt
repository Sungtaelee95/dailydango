package com.bhst.dailydango.oss

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.feature.menu.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun CreditScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.oss),
            style = DailyDangoTheme.typography.medium16
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CreditScreenPreview() {
    DailyDangoTheme {
        CreditScreen()
    }
}

@Composable
@Preview(showBackground = true, device = TABLET)
fun CreditScreenPreview2() {
    DailyDangoTheme {
        CreditScreen()
    }
}