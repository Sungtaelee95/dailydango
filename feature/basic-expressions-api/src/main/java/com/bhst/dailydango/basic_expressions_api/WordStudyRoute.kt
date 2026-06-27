package com.bhst.dailydango.basic_expressions_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bhst.dailydango.app.feature.basic.expressions.study.api.R
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data class WordStudyRoute(
    val chapter: Int,
    override val showBottomBar: Boolean = false,
    override val titleRes: Int = R.string.word
): Route {
    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.Back
    }

    @Composable
    override fun getTopBarTitle(): String {
        return "$chapter" + stringResource(R.string.chapter) + " " + stringResource(titleRes)
    }
}