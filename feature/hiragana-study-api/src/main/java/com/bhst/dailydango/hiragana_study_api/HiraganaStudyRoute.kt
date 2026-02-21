package com.bhst.dailydango.hiragana_study_api

import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data object HiraganaStudyRoute: Route {
    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.Home
    }
}