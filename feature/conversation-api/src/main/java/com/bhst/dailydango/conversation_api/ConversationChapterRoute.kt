package com.bhst.dailydango.conversation_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bhst.dailydango.app.feature.conversation.api.R
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data object ConversationChapterRoute: Route {
    override val titleRes: Int
        get() = R.string.word_and_grammar_study
    override val showBottomBar: Boolean
        get() = false

    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.Back
    }

    @Composable
    override fun getTopBarTitle(): String {
       return stringResource(titleRes)
    }

}