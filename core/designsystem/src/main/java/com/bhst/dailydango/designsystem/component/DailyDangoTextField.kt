package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun DailyDangoTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    readOnly: Boolean = false,
    label: @Composable () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly,

            // 1. 둥근 모서리 설정 (Pill shape)
            shape = RoundedCornerShape(8.dp),
            label = label,

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,

                // 테두리 색상 (포커스 여부 상관없이 연한 회색 유지)
                focusedBorderColor = MaterialTheme.colorScheme.outline,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,

                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,

                // 커서 색상
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions
        )
    }
}