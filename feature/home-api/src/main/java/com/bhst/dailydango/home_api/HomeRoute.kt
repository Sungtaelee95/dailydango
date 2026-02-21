package com.bhst.dailydango.home_api

import androidx.navigation3.runtime.NavKey
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute: Route {
    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.Home
    }
}