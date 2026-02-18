package com.bhst.dailydango.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import com.bhst.dailydango.home_api.HomeRoute

class DailyDangoAppState {
    val backStack = mutableStateListOf<Any>(HomeRoute)

    fun navigationTo(destination: Any) {
        backStack.add(destination)
    }

    fun onBack() {
        if (backStack.size > 1) { // 최소 1개는 남겨두기 같은 로직 추가 가능
            backStack.removeLastOrNull()
        }
    }
}

// 2. 이 클래스를 기억(Remember)해주는 Composable 함수
@Composable
fun rememberDailyDangoAppState(): DailyDangoAppState {
    return remember {
        DailyDangoAppState()
    }
}