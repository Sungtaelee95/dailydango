package com.bhst.dailydango.katakana_study_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bhst.dailydango.app.feature.gatakana.study.api.R
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.model.word_type.WordType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data class KatakanaDetailRoute(
    val wordType: WordType,
    val rowHeader: String,
    override val showBottomBar: Boolean = false,
    override val titleRes: Int = R.string.katakana
): Route {
    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.Back
    }

    @Composable
    override fun getTopBarTitle(): String {
        return stringResource(titleRes)
    }
}