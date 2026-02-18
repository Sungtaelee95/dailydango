package com.bhst.dailydango.main

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import com.bhst.dailydango.hiragana_study.HiraganaStudyScreen
import com.bhst.dailydango.hiragana_study_api.HiraganaStudyRoute
import com.bhst.dailydango.home.HomeScreen
import com.bhst.dailydango.home_api.HomeRoute

fun dailyDangoEntryProvider(
    navigateTo: (Any) -> Unit,
    back: () -> Unit
): (Any) -> NavEntry<Any> = entryProvider {
    entry<HomeRoute> {
        HomeScreen(
            navigateToHiraganaStudy = {
                navigateTo(HiraganaStudyRoute)
            },
            navigateToKatakanaStudy = {},
            navigateToGrammarStudy = {},
            navigateToGrammarTest = {},
            back = back
        )
    }
    entry<HiraganaStudyRoute> {
        HiraganaStudyScreen()
    }
}