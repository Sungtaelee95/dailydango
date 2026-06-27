package com.bhst.dailydango.quiz.entry

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.navigation3.runtime.EntryProviderScope
import androidx.window.core.layout.WindowSizeClass
import com.bhst.dailydango.quiz.chapter.QuizChapterScreen
import com.bhst.dailydango.quiz.chapter.QuizChapterTabScreen
import com.bhst.dailydango.quiz.quiz.QuizScreen
import com.bhst.dailydango.quiz.quiz.QuizTabScreen
import com.bhst.dailydango.quiz.tip.QuizTipScreen
import com.bhst.dailydango.quiz.tip.QuizTipTabScreen
import com.bhst.dailydango.quiz_api.QuizChapterRoute
import com.bhst.dailydango.quiz_api.QuizRoute
import com.bhst.dailydango.route_api.Route

fun EntryProviderScope<Route>.quizEntry(
    navigateToQuizChapter: () -> Unit = {},
    navigateToChapter: (Int) -> Unit = {},
    back: () -> Unit = {}
) {

    entry<QuizChapterRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                QuizChapterTabScreen(
                    navigateToChapter = navigateToChapter
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                QuizChapterTabScreen(
                    navigateToChapter = navigateToChapter
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                QuizChapterScreen(
                    navigateToChapter = navigateToChapter
                )
            }
        }
    }

    entry<QuizRoute> { entry ->
        val chapter = entry.chapter
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                QuizTabScreen(
                    back = back,
                    chapter = chapter
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                QuizTabScreen(
                    back = back,
                    chapter = chapter
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                QuizScreen(
                    back = back,
                    chapter = chapter
                )
            }
        }
    }
}