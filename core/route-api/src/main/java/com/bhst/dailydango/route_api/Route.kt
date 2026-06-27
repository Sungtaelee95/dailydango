package com.bhst.dailydango.route_api

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType

interface Route: NavKey {
    @get:StringRes
    val titleRes: Int

    val showBottomBar: Boolean
    fun getTopBar(): TopAppBarNavigationType

    @Composable
    fun getTopBarTitle(): String
}