package com.bhst.dailydango.entry

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.navigation3.runtime.EntryProviderScope
import androidx.window.core.layout.WindowSizeClass
import com.bhst.dailydango.favorite.FavoriteContentScreen
import com.bhst.dailydango.favorite.FavoriteContentTabScreen
import com.bhst.dailydango.menu.MenuScreen
import com.bhst.dailydango.menu.MenuTabScreen
import com.bhst.dailydango.menu_api.FavoriteContentsRoute
import com.bhst.dailydango.menu_api.MenuRoute
import com.bhst.dailydango.menu_api.OssRoute
import com.bhst.dailydango.menu_api.PlayRepeatRoute
import com.bhst.dailydango.menu_api.PlaySpeedRoute
import com.bhst.dailydango.menu_api.ThemeRoute
import com.bhst.dailydango.oss.CreditScreen
import com.bhst.dailydango.play_repeat.PlayRepeatScreen
import com.bhst.dailydango.play_repeat.PlayRepeatTabScreen
import com.bhst.dailydango.play_speed.PlaySpeedScreen
import com.bhst.dailydango.play_speed.PlaySpeedTabScreen
import com.bhst.dailydango.route_api.Route
import com.bhst.dailydango.theme.ThemeScreen
import com.bhst.dailydango.theme.ThemeTabScreen

fun EntryProviderScope<Route>.menuEntries(
    navigateToFavorite: () -> Unit,
    navigateToTheme: () -> Unit,
    navigateToPlaySpeed: () -> Unit,
    navigateToPlayRepeat: () -> Unit,
    navigateToOss: () -> Unit,
    navigateToHanjaDetail: (List<String>) -> Unit,
) {
    entry<MenuRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                MenuTabScreen(
                    navigateToFavorite = navigateToFavorite,
                    navigateToTheme = navigateToTheme,
                    navigateToPlaySpeed = navigateToPlaySpeed,
                    navigateToPlayRepeat = navigateToPlayRepeat,
                    navigateToOss = navigateToOss
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                MenuTabScreen(
                    navigateToFavorite = navigateToFavorite,
                    navigateToTheme = navigateToTheme,
                    navigateToPlaySpeed = navigateToPlaySpeed,
                    navigateToPlayRepeat = navigateToPlayRepeat,
                    navigateToOss = navigateToOss
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                MenuScreen(
                    navigateToFavorite = navigateToFavorite,
                    navigateToTheme = navigateToTheme,
                    navigateToPlaySpeed = navigateToPlaySpeed,
                    navigateToPlayRepeat = navigateToPlayRepeat,
                    navigateToOss = navigateToOss
                )
            }
        }
    }
    entry<FavoriteContentsRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                FavoriteContentTabScreen(
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                FavoriteContentTabScreen(
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                FavoriteContentScreen(
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
        }

    }
    entry<ThemeRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                ThemeTabScreen()
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                ThemeTabScreen()
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                ThemeScreen()
            }
        }
    }
    entry<PlaySpeedRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                PlaySpeedTabScreen()
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                PlaySpeedTabScreen()
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                PlaySpeedScreen()
            }
        }
    }

    entry<PlayRepeatRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfoV2()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                PlayRepeatTabScreen()
            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                PlayRepeatTabScreen()
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                PlayRepeatScreen()
            }
        }
    }

    entry<OssRoute> {
        CreditScreen()
    }
}