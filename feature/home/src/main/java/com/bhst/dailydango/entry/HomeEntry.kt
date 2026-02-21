package com.bhst.dailydango.entry

import androidx.navigation3.runtime.EntryProviderScope
import com.bhst.dailydango.home.HomeScreen
import com.bhst.dailydango.home_api.HomeRoute

fun EntryProviderScope<Any>.homeEntries(
    navigateToHiraganaStudy: () -> Unit,
    navigateToKatakanaStudy: () -> Unit,
    navigateToGrammarStudy: () -> Unit,
    navigateToGrammarTest: () -> Unit,
    back: () -> Unit,
) {
    entry<HomeRoute> {
        HomeScreen(
            navigateToHiraganaStudy =  navigateToHiraganaStudy,
            navigateToKatakanaStudy = navigateToKatakanaStudy,
            navigateToGrammarStudy = navigateToGrammarStudy,
            navigateToGrammarTest = navigateToGrammarTest,
            back = back
        )
    }
}