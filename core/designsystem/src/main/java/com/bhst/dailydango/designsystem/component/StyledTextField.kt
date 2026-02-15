package com.somuna.app.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun StyledTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    placeholder: String = "",
    placeholderStyle: TextStyle = DailyDangoTheme.typography.medium10,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    style: TextStyle = DailyDangoTheme.typography.medium10,
    icon: ImageVector? = null,
    iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    backGroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    shape: Int = 4,
    innerPadding: Int = 12
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        enabled = enabled,
        modifier = modifier
            .height(28.dp) // 높이를 조금 키워 터치 영역 확보
            .background(
                color = backGroundColor,
                shape = RoundedCornerShape(shape.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline, // 연한 회색 테두리
                shape = RoundedCornerShape(shape.dp)
            ),
        singleLine = true,
        textStyle = style.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = innerPadding.dp), // 내부 여백
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Icon",
                        tint = iconTint,
                        modifier = Modifier.size(18.dp) // 아이콘 크기 조절
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // 아이콘과 텍스트 사이 간격
                }

                Box(
                    modifier = Modifier.weight(1f), // 남은 영역 채우기
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty() && placeholder.isNotEmpty()) {
                        Text(
                            text = placeholder,
                            style = placeholderStyle,
                            color = MaterialTheme.colorScheme.surfaceDim // 연한 회색 (플레이스홀더)
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}