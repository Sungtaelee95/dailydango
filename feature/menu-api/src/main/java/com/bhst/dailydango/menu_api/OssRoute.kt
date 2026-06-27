package com.bhst.dailydango.menu_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bhst.dailydango.app.feature.menu.api.R
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data object OssRoute: Route {
    override val titleRes: Int
        get() = R.string.license
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