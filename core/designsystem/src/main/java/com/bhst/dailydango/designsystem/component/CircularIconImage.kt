package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun CircularIconContainer(
    imageRes: Int,
    size: Dp = 137.dp,         // 원 크기
    elevation: Dp = 8.dp       // 그림자
) {
    Surface(
        modifier = Modifier.size(size),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.background,   // 배경색
        shadowElevation = elevation
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(size * 0.55f) // 중앙 이미지 크기 비율
            )
        }
    }
}