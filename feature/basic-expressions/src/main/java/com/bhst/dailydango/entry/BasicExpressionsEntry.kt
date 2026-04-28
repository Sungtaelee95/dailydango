package com.bhst.dailydango.entry

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.navigation3.runtime.EntryProviderScope
import androidx.window.core.layout.WindowSizeClass
import com.bhst.dailydango.basic_expressions.chapter_select.BasicExpressionsScreen
import com.bhst.dailydango.basic_expressions.chapter_select.BasicExpressionsTabScreen
import com.bhst.dailydango.basic_expressions.chapter_tip.ChapterTipScreen
import com.bhst.dailydango.basic_expressions.chapter_tip.ChapterTipTabScreen
import com.bhst.dailydango.basic_expressions.sentnece_study.SentenceStudyScreen
import com.bhst.dailydango.basic_expressions.sentnece_study.SentenceStudyTabScreen
import com.bhst.dailydango.basic_expressions.word_study.WordStudyScreen
import com.bhst.dailydango.basic_expressions.word_study.WordStudyTabScreen
import com.bhst.dailydango.basic_expressions_api.BasicExpressionsRoute
import com.bhst.dailydango.basic_expressions_api.ChapterTipRoute
import com.bhst.dailydango.basic_expressions_api.SentenceStudyRoute
import com.bhst.dailydango.basic_expressions_api.WordStudyRoute
import com.bhst.dailydango.route_api.Route

fun EntryProviderScope<Route>.basicExpressionsEntries(
    navigateToChapter: (Int) -> Unit,
    navigateToSentenceStudy: (Int) -> Unit,
    navigateToWordStudy: (Int) -> Unit,
    navigateToHanjaDetail: (List<String>) -> Unit
) {
    entry<BasicExpressionsRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                BasicExpressionsTabScreen(
                    navigateToChapter = navigateToChapter,
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                BasicExpressionsTabScreen(
                    navigateToChapter = navigateToChapter,
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                BasicExpressionsScreen(
                    navigateToChapter = navigateToChapter,
                )
            }
        }
    }
    entry<ChapterTipRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                ChapterTipTabScreen(
                    chapter = it.chapter,
                    navigateToSentenceStudy = navigateToSentenceStudy,
                    navigateToWordStudy = navigateToWordStudy
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                ChapterTipTabScreen(
                    chapter = it.chapter,
                    navigateToSentenceStudy = navigateToSentenceStudy,
                    navigateToWordStudy = navigateToWordStudy
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                ChapterTipScreen(
                    chapter = it.chapter,
                    navigateToSentenceStudy = navigateToSentenceStudy,
                    navigateToWordStudy = navigateToWordStudy
                )
            }
        }
    }

    entry<SentenceStudyRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                SentenceStudyTabScreen(
                    chapter = it.chapter,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                SentenceStudyTabScreen(
                    chapter = it.chapter,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                SentenceStudyScreen(
                    chapter = it.chapter,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
        }
    }

    entry<WordStudyRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass

        // 🚀 최신 권장 방식: isWidthAtLeastBreakpoint() 사용
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                WordStudyTabScreen(
                    chapter = it.chapter,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                WordStudyTabScreen(
                    chapter = it.chapter,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                WordStudyScreen(
                    chapter = it.chapter,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
        }
    }
}