package com.bhst.dailydango.entry

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.navigation3.runtime.EntryProviderScope
import androidx.window.core.layout.WindowSizeClass
import com.bhst.dailydango.hiragana_detail.HiraganaDetailScreen
import com.bhst.dailydango.hiragana_detail.HiraganaDetailTabScreen
import com.bhst.dailydango.hiragana_study.HiraganaStudyScreen
import com.bhst.dailydango.hiragana_study.HiraganaStudyTabScreen
import com.bhst.dailydango.hiragana_study_api.HiraganaDetailRoute
import com.bhst.dailydango.hiragana_study_api.HiraganaStudyRoute
import com.bhst.dailydango.model.word_type.WordType
import com.bhst.dailydango.route_api.Route

fun EntryProviderScope<Route>.hiraganaStudyEntries(
    navigateToHiraganaDetail: (WordType, String) -> Unit,
) {
    entry<HiraganaStudyRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                HiraganaStudyTabScreen(
                    navigateToHiraganaDetail = navigateToHiraganaDetail
                )

            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                HiraganaStudyTabScreen(
                    navigateToHiraganaDetail = navigateToHiraganaDetail
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                HiraganaStudyScreen(
                    navigateToHiraganaDetail = navigateToHiraganaDetail
                )
            }
        }
    }
    entry<HiraganaDetailRoute> { entry ->
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                HiraganaDetailTabScreen(
                    wordType = entry.wordType,
                    rowHeader = entry.rowHeader
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                HiraganaDetailTabScreen(
                    wordType = entry.wordType,
                    rowHeader = entry.rowHeader
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                HiraganaDetailScreen(
                    wordType = entry.wordType,
                    rowHeader = entry.rowHeader
                )
            }
        }
    }
}