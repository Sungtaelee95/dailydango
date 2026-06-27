package com.bhst.dailydango.home_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import com.bhst.dailydango.app.feature.home.api.R
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute: Route {
    override val titleRes: Int
        get() = R.string.study
    override val showBottomBar: Boolean
        get() = true

    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.None
    }

    @Composable
    override fun getTopBarTitle(): String {
        return stringResource(titleRes)
    }
}