package com.bhst.dailydango.entry

import androidx.navigation3.runtime.EntryProviderScope
import com.bhst.dailydango.home.HomeScreen
import com.bhst.dailydango.home_api.HomeRoute
import com.bhst.dailydango.route_api.Route

fun EntryProviderScope<Route>.homeEntries(
    navigateToHiraganaStudy: () -> Unit,
    navigateToKatakanaStudy: () -> Unit,
    navigateToGrammarStudy: () -> Unit,
    navigateToGrammarTest: () -> Unit,
    navigateToSearch: () -> Unit
) {
    entry<HomeRoute> {
        HomeScreen(
            navigateToHiraganaStudy =  navigateToHiraganaStudy,
            navigateToKatakanaStudy = navigateToKatakanaStudy,
            navigateToGrammarStudy = navigateToGrammarStudy,
            navigateToGrammarTest = navigateToGrammarTest,
            navigateToSearch = navigateToSearch
        )
    }
}