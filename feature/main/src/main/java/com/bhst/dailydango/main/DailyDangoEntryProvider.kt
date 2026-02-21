package com.bhst.dailydango.main

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import com.bhst.dailydango.entry.homeEntries
import com.bhst.dailydango.hiragana_study.HiraganaStudyScreen
import com.bhst.dailydango.hiragana_study_api.HiraganaStudyRoute

fun dailyDangoEntryProvider(
    navigateTo: (Any) -> Unit,
    back: () -> Unit
): (Any) -> NavEntry<Any> = entryProvider {
    homeEntries(
        navigateToHiraganaStudy = { navigateTo(HiraganaStudyRoute) },
        navigateToKatakanaStudy = { },
        navigateToGrammarStudy = {},
        navigateToGrammarTest = { },
        back = back
    )
    entry<HiraganaStudyRoute> {
        HiraganaStudyScreen()
    }
}