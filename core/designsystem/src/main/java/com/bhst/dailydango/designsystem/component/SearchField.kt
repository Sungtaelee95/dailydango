package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun NotOutLineSearchField(
    searchText: String = "",
    onValueChange: (String) -> Unit = {},
    hint: Int = 0
) {
    // 이미지의 배경색과 비슷하게 설정 (테스트용)
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),

            // 1. 둥근 모서리 설정 (Pill shape)
            shape = RoundedCornerShape(16.dp),

            // 2. 돋보기 아이콘
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.outline // 아이콘 회색
                )
            },

            // 3. 힌트 텍스트 (Placeholder)
            placeholder = {
                Text(
                    text = stringResource(hint),
                    color = MaterialTheme.colorScheme.outline // 텍스트 회색
                )
            },

            // 4. 색상 커스텀 (테두리, 배경, 커서 등)
            colors = OutlinedTextFieldDefaults.colors(
                // 배경색을 흰색으로 고정
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.onPrimary,

                // 테두리 색상 (포커스 여부 상관없이 연한 회색 유지)
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,

                // 커서 색상
                cursorColor = Color.Gray
            ),
            singleLine = true
        )
    }
}

@Composable
fun ManufacturerSearchField(
    searchText: String = "",
    onValueChange: (String) -> Unit = {},
    hint: Int = 0
) {
    // 이미지의 배경색과 비슷하게 설정 (테스트용)
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),

            // 1. 둥근 모서리 설정 (Pill shape)
            shape = RoundedCornerShape(16.dp),

            // 2. 돋보기 아이콘
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.outline // 아이콘 회색
                )
            },

            // 3. 힌트 텍스트 (Placeholder)
            placeholder = {
                Text(
                    text = stringResource(hint),
                    color = MaterialTheme.colorScheme.outline // 텍스트 회색
                )
            },

            // 4. 색상 커스텀 (테두리, 배경, 커서 등)
            colors = OutlinedTextFieldDefaults.colors(
                // 배경색을 흰색으로 고정
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.onPrimary,

                // 테두리 색상 (포커스 여부 상관없이 연한 회색 유지)
                focusedBorderColor = MaterialTheme.colorScheme.outline,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,

                // 커서 색상
                cursorColor = Color.Gray
            ),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ManufacturerSearchFieldPreview() {
    DailyDangoTheme {
        ManufacturerSearchField()
    }

}