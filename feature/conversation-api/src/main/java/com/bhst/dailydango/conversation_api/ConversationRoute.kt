package com.bhst.dailydango.conversation_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bhst.dailydango.app.feature.conversation.api.R
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data class ConversationRoute(
    val chapter: Int,
    override val titleRes: Int = R.string.grammar,
    override val showBottomBar: Boolean = false
) : Route {
    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.Back
    }

    @Composable
    override fun getTopBarTitle(): String {
        return "$chapter" + " " + stringResource(R.string.chapter) + " " + stringResource(R.string.grammar)
    }
}
