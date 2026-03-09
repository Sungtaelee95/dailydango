package com.bhst.dailydango.main

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import com.bhst.dailydango.basic_expressions_api.BasicExpressionsRoute
import com.bhst.dailydango.basic_expressions_api.ChapterTipRoute
import com.bhst.dailydango.basic_expressions_api.SentenceStudyRoute
import com.bhst.dailydango.basic_expressions_api.WordStudyRoute
import com.bhst.dailydango.entry.basicExpressionsEntries
import com.bhst.dailydango.entry.hiraganaStudyEntries
import com.bhst.dailydango.entry.homeEntries
import com.bhst.dailydango.entry.katakanaStudyEntries
import com.bhst.dailydango.entry.levelTestEntries
import com.bhst.dailydango.entry.menuEntries
import com.bhst.dailydango.hiragana_katakana_tip.entry.hiraganaKatakanaTipEntry
import com.bhst.dailydango.hiragana_katakana_tip_api.HiraganaKatakanaTipRoute
import com.bhst.dailydango.hiragana_study_api.HiraganaDetailRoute
import com.bhst.dailydango.hiragana_study_api.HiraganaStudyRoute
import com.bhst.dailydango.katakana_study_api.KatakanaDetailRoute
import com.bhst.dailydango.katakana_study_api.KatakanaStudyRoute
import com.bhst.dailydango.level_test_api.LevelTestRoute
import com.bhst.dailydango.menu_api.FavoriteContentsRoute
import com.bhst.dailydango.menu_api.PlaySpeedRoute
import com.bhst.dailydango.menu_api.ThemeRoute
import com.bhst.dailydango.route_api.Route
import com.bhst.dailydango.search.entry.searchEntries
import com.bhst.dailydango.search_api.SearchRoute

fun dailyDangoEntryProvider(
    navigateTo: (Route) -> Unit,
    back: () -> Unit
): (Route) -> NavEntry<Route> = entryProvider {
    homeEntries(
        navigateToHiraganaKatakanaTip = { navigateTo(HiraganaKatakanaTipRoute) },
        navigateToGrammarStudy = { navigateTo(BasicExpressionsRoute) },
        navigateToGrammarTest = { navigateTo(LevelTestRoute) },
        navigateToSearch = { navigateTo(SearchRoute) }
    )
    menuEntries(
        navigateToFavorite = { navigateTo(FavoriteContentsRoute) },
        navigateToTheme = { navigateTo(ThemeRoute) },
        navigateToPlaySpeed = { navigateTo(PlaySpeedRoute) }
    )
    searchEntries()
    hiraganaStudyEntries(
        navigateToHiraganaDetail = { wordType, rowHeader ->
            navigateTo(HiraganaDetailRoute(wordType, rowHeader))
        }
    )
    katakanaStudyEntries(
        navigateToKatakanaDetail = { wordType, rowHeader ->
            navigateTo(KatakanaDetailRoute(wordType, rowHeader))
        }
    )
    basicExpressionsEntries(
        navigateToChapter = { chapter -> navigateTo(ChapterTipRoute(chapter)) },
        navigateToSentenceStudy = { chapter -> navigateTo(SentenceStudyRoute(chapter)) },
        navigateToWordStudy = { chapter -> navigateTo(WordStudyRoute(chapter)) }
    )
    levelTestEntries()
    hiraganaKatakanaTipEntry(
        navigateToHiraganaStudy = { navigateTo(HiraganaStudyRoute) },
        navigateToKatakanaStudy = { navigateTo(KatakanaStudyRoute) }
    )
}