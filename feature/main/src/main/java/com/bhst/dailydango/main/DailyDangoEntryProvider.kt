package com.bhst.dailydango.main

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import com.bhst.dailydango.entry.hiraganaStudyEntries
import com.bhst.dailydango.entry.homeEntries
import com.bhst.dailydango.entry.katakanaStudyEntries
import com.bhst.dailydango.hiragana_study_api.HiraganaStudyRoute
import com.bhst.dailydango.katakana_study_api.KatakanaStudyRoute
import com.bhst.dailydango.menu.entry.menuEntries
import com.bhst.dailydango.route_api.Route
import com.bhst.dailydango.search.entry.searchEntries
import com.bhst.dailydango.search_api.SearchRoute

fun dailyDangoEntryProvider(
    navigateTo: (Route) -> Unit,
    back: () -> Unit
): (Route) -> NavEntry<Route> = entryProvider {
    homeEntries(
        navigateToHiraganaStudy = { navigateTo(HiraganaStudyRoute) },
        navigateToKatakanaStudy = { navigateTo(KatakanaStudyRoute) },
        navigateToGrammarStudy = { },
        navigateToGrammarTest = { },
        navigateToSearch = { navigateTo(SearchRoute) }
    )
    menuEntries()
    searchEntries()
    hiraganaStudyEntries()
    katakanaStudyEntries()
}