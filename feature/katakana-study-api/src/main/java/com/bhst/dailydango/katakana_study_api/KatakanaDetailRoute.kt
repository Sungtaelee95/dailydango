package com.bhst.dailydango.katakana_study_api

import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.model.word_type.WordType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data class KatakanaDetailRoute(
    val wordType: WordType,
    val rowHeader: String
): Route {
    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.Back
    }
}