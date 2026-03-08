package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun MenuCard(
    img: Int = R.drawable.top_bar_logo,
    title: String = "",
    subTitle: String = "",
    onClick: () -> Unit = {}
) {
    DailyDangoElevationCard(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = 20.dp,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageCard(
                painter = painterResource(id = img),
                contentDescription = "menu_img",
                modifier = Modifier.size(100.dp)
            )
            Spacer(
                modifier = Modifier.width(12.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = DailyDangoTheme.typography.bold20,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    text = subTitle,
                    style = DailyDangoTheme.typography.medium16,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Go to chapter",
                modifier = Modifier.size(36.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MenuCardPreview(){
    DailyDangoTheme {
        MenuCard()
    }
}