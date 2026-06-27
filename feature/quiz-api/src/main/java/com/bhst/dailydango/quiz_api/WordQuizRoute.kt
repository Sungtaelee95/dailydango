package com.bhst.dailydango.quiz_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bhst.dailydango.app.feature.level.quiz.api.R
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data class WordQuizRoute(
    val chapter: Int,
    override val titleRes: Int = R.string.word,
    override val showBottomBar: Boolean = false
) : Route {
    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.Back
    }

    @Composable
    override fun getTopBarTitle(): String {
        return "$chapter" + stringResource(R.string.chapter) + " " + stringResource(R.string.word) + stringResource(
            R.string.test
        )
    }
}