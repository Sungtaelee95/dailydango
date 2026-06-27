package com.bhst.dailydango.entry

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.navigation3.runtime.EntryProviderScope
import androidx.window.core.layout.WindowSizeClass
import com.bhst.dailydango.home.HomeScreen
import com.bhst.dailydango.home.HomeTabScreen
import com.bhst.dailydango.home_api.HomeRoute
import com.bhst.dailydango.route_api.Route

fun EntryProviderScope<Route>.homeEntries(
    navigateToGrammarStudy: () -> Unit,
    navigateToQuiz: () -> Unit = {},
    navigateToSearch: () -> Unit,
    navigateToConversation: () -> Unit = {},
) {
    entry<HomeRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                HomeTabScreen(
                    navigateToGrammarStudy = navigateToGrammarStudy,
                    navigateToQuiz = {},
                    navigateToSearch = navigateToSearch,
                    navigateToConversation = {}
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                HomeTabScreen(
                    navigateToGrammarStudy = navigateToGrammarStudy,
                    navigateToQuiz = {  },
                    navigateToSearch = navigateToSearch,
                    navigateToConversation = {  }
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                HomeScreen(
                    navigateToGrammarStudy = navigateToGrammarStudy,
                    navigateToQuiz = {  },
                    navigateToSearch = navigateToSearch,
                    navigateToConversation = {  }
                )
            }
        }
    }
}