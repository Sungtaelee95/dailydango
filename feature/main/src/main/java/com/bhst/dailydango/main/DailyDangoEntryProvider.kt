package com.bhst.dailydango.main

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import com.bhst.dailydango.basic_expressions_api.BasicExpressionsRoute
import com.bhst.dailydango.basic_expressions_api.ChapterTipRoute
import com.bhst.dailydango.basic_expressions_api.SentenceStudyRoute
import com.bhst.dailydango.basic_expressions_api.WordStudyRoute
import com.bhst.dailydango.conversation.entry.conversationEntry
import com.bhst.dailydango.conversation_api.ConversationChapterRoute
import com.bhst.dailydango.conversation_api.ConversationRoute
import com.bhst.dailydango.conversation_api.ConversationTipRoute
import com.bhst.dailydango.entry.basicExpressionsEntries
import com.bhst.dailydango.entry.hiraganaStudyEntries
import com.bhst.dailydango.entry.homeEntries
import com.bhst.dailydango.entry.katakanaStudyEntries
import com.bhst.dailydango.entry.levelTestEntries
import com.bhst.dailydango.entry.menuEntries
import com.bhst.dailydango.entry.suggestionEntry
import com.bhst.dailydango.hanja_detail.entry.hanjaDetailEntries
import com.bhst.dailydango.hiragana_katakana_tip.entry.hiraganaKatakanaTipEntry
import com.bhst.dailydango.hiragana_katakana_tip_api.HiraganaKatakanaTipRoute
import com.bhst.dailydango.hiragana_study_api.HiraganaDetailRoute
import com.bhst.dailydango.hiragana_study_api.HiraganaStudyRoute
import com.bhst.dailydango.katakana_study_api.KatakanaDetailRoute
import com.bhst.dailydango.katakana_study_api.KatakanaStudyRoute
import com.bhst.dailydango.menu_api.FavoriteContentsRoute
import com.bhst.dailydango.menu_api.OssRoute
import com.bhst.dailydango.menu_api.PlayRepeatRoute
import com.bhst.dailydango.menu_api.PlaySpeedRoute
import com.bhst.dailydango.menu_api.ThemeRoute
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.quiz.entry.quizEntry
import com.bhst.dailydango.quiz_api.QuizChapterRoute
import com.bhst.dailydango.quiz_api.QuizTipRoute
import com.bhst.dailydango.route_api.Route
import com.bhst.dailydango.search.entry.searchEntries
import com.bhst.dailydango.search_api.SearchRoute
import com.bhst.hanja_detail_api.HanjaDetailRoute

fun dailyDangoEntryProvider(
    navigateTo: (Route) -> Unit,
    back: () -> Unit,
    contents: List<ContentState> = emptyList()
): (Route) -> NavEntry<Route> = entryProvider {
    homeEntries(
        navigateToHiraganaKatakanaTip = { navigateTo(HiraganaKatakanaTipRoute) },
        navigateToGrammarStudy = { navigateTo(BasicExpressionsRoute) },
        navigateToQuiz = { navigateTo(QuizTipRoute) },
        navigateToSearch = { navigateTo(SearchRoute) },
        navigateToConversation = { navigateTo(ConversationTipRoute) }
    )
    menuEntries(
        navigateToFavorite = { navigateTo(FavoriteContentsRoute) },
        navigateToTheme = { navigateTo(ThemeRoute) },
        navigateToPlaySpeed = { navigateTo(PlaySpeedRoute) },
        navigateToPlayRepeat = { navigateTo(PlayRepeatRoute) },
        navigateToHanjaDetail = { hanjas -> navigateTo(HanjaDetailRoute(hanjas)) },
        navigateToOss = { navigateTo(OssRoute) }
    )
    searchEntries(
        contents = contents,
        navigateToHanjaDetail = { hanjas -> navigateTo(HanjaDetailRoute(hanjas)) }
    )
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
        navigateToWordStudy = { chapter -> navigateTo(WordStudyRoute(chapter)) },
        navigateToHanjaDetail = { hanjas -> navigateTo(HanjaDetailRoute(hanjas)) }
    )
    levelTestEntries()
    hiraganaKatakanaTipEntry(
        navigateToHiraganaStudy = { navigateTo(HiraganaStudyRoute) },
        navigateToKatakanaStudy = { navigateTo(KatakanaStudyRoute) }
    )
    hanjaDetailEntries()
    suggestionEntry(
        onBack = back
    )
    conversationEntry(
        navigateToConversationChapter = { navigateTo(ConversationChapterRoute) },
        navigateToChapter = { chapter -> navigateTo(ConversationRoute(chapter)) },
        navigateToHanjaDetail = { hanjas -> navigateTo(HanjaDetailRoute(hanjas)) }
    )
    quizEntry(
        navigateToQuizChapter = { navigateTo(QuizChapterRoute) }
    )
}