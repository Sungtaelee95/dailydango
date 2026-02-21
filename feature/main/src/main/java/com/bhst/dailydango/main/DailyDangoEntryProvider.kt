package com.bhst.dailydango.main

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import com.bhst.dailydango.entry.homeEntries
import com.bhst.dailydango.hiragana_study.HiraganaStudyScreen
import com.bhst.dailydango.hiragana_study_api.HiraganaStudyRoute
import com.bhst.dailydango.menu.entry.menuEntry
import com.bhst.dailydango.route_api.Route
import com.bhst.dailydango.search.entry.searchEntry
import com.bhst.dailydango.search_api.SearchRoute

fun dailyDangoEntryProvider(
    navigateTo: (Route) -> Unit,
    back: () -> Unit
): (Route) -> NavEntry<Route> = entryProvider {
    homeEntries(
        navigateToHiraganaStudy = { navigateTo(HiraganaStudyRoute) },
        navigateToKatakanaStudy = { },
        navigateToGrammarStudy = {},
        navigateToGrammarTest = { },
        navigateToSearch = { navigateTo(SearchRoute) }
    )
    menuEntry()
    searchEntry()
    entry<HiraganaStudyRoute> {
        HiraganaStudyScreen()
    }
}