package com.bhst.dailydango.entry

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.navigation3.runtime.EntryProviderScope
import androidx.window.core.layout.WindowSizeClass
import com.bhst.dailydango.favorite.FavoriteContentScreen
import com.bhst.dailydango.menu.MenuScreen
import com.bhst.dailydango.menu_api.FavoriteContentsRoute
import com.bhst.dailydango.menu_api.MenuRoute
import com.bhst.dailydango.menu_api.PlaySpeedRoute
import com.bhst.dailydango.menu_api.ThemeRoute
import com.bhst.dailydango.play_speed.PlaySpeedScreen
import com.bhst.dailydango.route_api.Route
import com.bhst.dailydango.theme.ThemeScreen

fun EntryProviderScope<Route>.menuEntries(
    navigateToFavorite: () -> Unit,
    navigateToTheme: () -> Unit,
    navigateToPlaySpeed: () -> Unit
) {
    entry<MenuRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {

            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                MenuScreen(
                    navigateToFavorite = navigateToFavorite,
                    navigateToTheme = navigateToTheme,
                    navigateToPlaySpeed = navigateToPlaySpeed
                )
            }
        }

    }
    entry<FavoriteContentsRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {

            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                FavoriteContentScreen()
            }
        }

    }
    entry<ThemeRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {

            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                ThemeScreen()
            }
        }
    }
    entry<PlaySpeedRoute> {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val windowSizeClass = adaptiveInfo.windowSizeClass
        when {
            // 1. 가로 너비가 EXPANDED(보통 840dp) 이상일 때 -> 태블릿 / 데스크탑 모드
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {

            }
            // 2. 가로 너비가 MEDIUM(보통 600dp) 이상일 때 -> 기기 가로 모드 / 폴더블폰
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
            }
            // 3. 그 외 (600dp 미만) -> COMPACT (일반 스마트폰 세로 모드)
            else -> {
                PlaySpeedScreen()
            }
        }
    }
}