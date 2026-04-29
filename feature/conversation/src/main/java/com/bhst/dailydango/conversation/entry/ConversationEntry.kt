package com.bhst.dailydango.conversation.entry

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.navigation3.runtime.EntryProviderScope
import androidx.window.core.layout.WindowSizeClass
import com.bhst.dailydango.conversation.chapter.ConversationChapterScreen
import com.bhst.dailydango.conversation.chapter.ConversationChapterTabScreen
import com.bhst.dailydango.conversation.conversation.ConversationScreen
import com.bhst.dailydango.conversation.conversation.ConversationTabScreen
import com.bhst.dailydango.conversation.tip.ConversationTipScreen
import com.bhst.dailydango.conversation.tip.ConversationTipTabScreen
import com.bhst.dailydango.conversation_api.ConversationChapterRoute
import com.bhst.dailydango.conversation_api.ConversationRoute
import com.bhst.dailydango.conversation_api.ConversationTipRoute
import com.bhst.dailydango.route_api.Route

fun EntryProviderScope<Route>.conversationEntry(
    navigateToConversationChapter: () -> Unit = {},
    navigateToChapter: (Int) -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit
) {
    entry<ConversationTipRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                ConversationTipTabScreen(
                    navigateToConversationChapter = navigateToConversationChapter,
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                ConversationTipTabScreen(
                    navigateToConversationChapter = navigateToConversationChapter,
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                ConversationTipScreen(
                    navigateToConversationChapter = navigateToConversationChapter,
                )
            }
        }
    }

    entry<ConversationChapterRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                ConversationChapterTabScreen(
                    navigateToChapter = navigateToChapter
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                ConversationChapterTabScreen(
                    navigateToChapter = navigateToChapter
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                ConversationChapterScreen(
                    navigateToChapter = navigateToChapter
                )
            }
        }
    }

    entry<ConversationRoute> { entry ->
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                ConversationTabScreen(
                    chapter = entry.chapter,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                ConversationTabScreen(
                    chapter = entry.chapter,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                ConversationScreen(
                    chapter = entry.chapter,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
        }
    }
}