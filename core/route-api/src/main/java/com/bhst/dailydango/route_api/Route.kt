package com.bhst.dailydango.route_api

import androidx.navigation3.runtime.NavKey
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType

interface Route: NavKey {
    fun getTopBar(): TopAppBarNavigationType
}