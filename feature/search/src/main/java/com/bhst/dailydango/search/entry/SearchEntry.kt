package com.bhst.dailydango.search.entry

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.navigation3.runtime.EntryProviderScope
import androidx.window.core.layout.WindowSizeClass
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.route_api.Route
import com.bhst.dailydango.search.screen.SearchScreen
import com.bhst.dailydango.search.screen.SearchTabScreen
import com.bhst.dailydango.search_api.SearchRoute

fun EntryProviderScope<Route>.searchEntries(
    contents: List<ContentState> = emptyList(),
    navigateToHanjaDetail: (List<String>) -> Unit = {},
) {
    entry<SearchRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                SearchTabScreen(
                    content = contents,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                SearchTabScreen(
                    content = contents,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                SearchScreen(
                    content = contents,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
        }
    }
}