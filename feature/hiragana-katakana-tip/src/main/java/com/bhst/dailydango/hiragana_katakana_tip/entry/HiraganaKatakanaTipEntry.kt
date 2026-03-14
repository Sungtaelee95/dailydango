package com.bhst.dailydango.hiragana_katakana_tip.entry

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.navigation3.runtime.EntryProviderScope
import androidx.window.core.layout.WindowSizeClass
import com.bhst.dailydango.hiragana_katakana_tip.screen.HiraganaKatakanaTipScreen
import com.bhst.dailydango.hiragana_katakana_tip.screen.HiraganaKatakanaTipTabScreen
import com.bhst.dailydango.hiragana_katakana_tip_api.HiraganaKatakanaTipRoute
import com.bhst.dailydango.route_api.Route

fun EntryProviderScope<Route>.hiraganaKatakanaTipEntry(
    navigateToHiraganaStudy: () -> Unit = {},
    navigateToKatakanaStudy: () -> Unit = {}
) {
    entry<HiraganaKatakanaTipRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                HiraganaKatakanaTipTabScreen(
                    navigateToHiraganaStudy = navigateToHiraganaStudy,
                    navigateToKatakanaStudy = navigateToKatakanaStudy
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                HiraganaKatakanaTipTabScreen(
                    navigateToHiraganaStudy = navigateToHiraganaStudy,
                    navigateToKatakanaStudy = navigateToKatakanaStudy
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                HiraganaKatakanaTipScreen(
                    navigateToHiraganaStudy = navigateToHiraganaStudy,
                    navigateToKatakanaStudy = navigateToKatakanaStudy
                )
            }
        }
    }
}