package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangeSelector(
    startDate: Long?,
    endDate: Long?,
    onStartDateChanged: (Long) -> Unit,
    onEndDateChanged: (Long) -> Unit
) {
    // 1. 다이얼로그 노출 여부 관리
    var showDatePicker by remember { mutableStateOf(false) }
    // 2. 현재 선택 중인 것이 시작일인지 종료일인지 구분
    var isPickingStartDate by remember { mutableStateOf(true) }

    // 3. DatePicker 상태 관리
    val datePickerState = rememberDatePickerState()

    // 4. 다이얼로그 열기 헬퍼 함수
    // 클릭 시 해당 날짜로 캘린더 상태를 업데이트하고 다이얼로그를 엽니다.
    fun openDatePicker(isStart: Boolean) {
        isPickingStartDate = isStart
        val targetDate = if (isStart) startDate else endDate

        // 이전에 선택된 날짜가 있으면 그 날짜로, 없으면 오늘 날짜로 세팅
        datePickerState.selectedDateMillis = targetDate ?: System.currentTimeMillis()
        datePickerState.displayedMonthMillis = targetDate ?: System.currentTimeMillis()

        showDatePicker = true
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // 시작일 버튼
        DateSelectionButton(
            dateMillis = startDate,
            onClick = { openDatePicker(true) }, // 시작일 모드로 열기
            modifier = Modifier
                .weight(1f)
                .height(28.dp)
        )

        Text(
            text = "~",
            modifier = Modifier.padding(horizontal = 8.dp),
            style = DailyDangoTheme.typography.light23, // 기존 테마 유지
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        // 종료일 버튼
        DateSelectionButton(
            dateMillis = endDate,
            onClick = { openDatePicker(false) }, // 종료일 모드로 열기
            modifier = Modifier
                .weight(1f)
                .height(28.dp)
        )
    }

    // 5. 요청하신 코드에 맞춘 DatePickerDialog
    if (showDatePicker) {
        DatePickerDialog(
            modifier = Modifier.scale(0.9f),
            onDismissRequest = { showDatePicker = false },
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            if (isPickingStartDate) {
                                onStartDateChanged(it)
                            } else {
                                onEndDateChanged(it)
                            }
                        }
                        showDatePicker = false
                    },
                ) {
                    Text(
                        text = stringResource(R.string.check),
                        style = DailyDangoTheme.typography.medium13
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = DailyDangoTheme.typography.medium13
                    )
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    todayContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    }
}

@Composable
fun DateSelectionButton(
    dateMillis: Long?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val displayText = remember(dateMillis) {
        dateMillis?.let { convertMillisToDate(it) }
            ?: convertMillisToDate(System.currentTimeMillis())
    }

    Surface(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(50)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(50)
            ),
        shape = RoundedCornerShape(50),
    ) {
        // 텍스트 잘림 방지를 위한 Box + Alignment.Center (지난번 수정 사항 유지)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = displayText,
                modifier = Modifier.padding(horizontal = 4.dp),
                textAlign = TextAlign.Center,
                style = DailyDangoTheme.typography.light12,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

// 날짜 변환 유틸
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    return formatter.format(Date(millis))
}