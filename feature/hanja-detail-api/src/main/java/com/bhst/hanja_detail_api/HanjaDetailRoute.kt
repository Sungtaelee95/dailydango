package com.bhst.hanja_detail_api

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bhst.dailydango.app.feature.hanja.detail.api.R
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.route_api.Route
import kotlinx.serialization.Serializable

@Serializable
data class HanjaDetailRoute(
    val hanjas: List<String>,
    override val titleRes: Int = R.string.hanja,
    override val showBottomBar: Boolean = false
): Route {
    override fun getTopBar(): TopAppBarNavigationType {
        return TopAppBarNavigationType.Back
    }

    @Composable
    override fun getTopBarTitle(): String {
        return stringResource(titleRes)
    }
}