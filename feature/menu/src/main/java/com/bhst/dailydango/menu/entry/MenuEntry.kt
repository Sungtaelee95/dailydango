package com.bhst.dailydango.menu.entry

import androidx.navigation3.runtime.EntryProviderScope
import com.bhst.dailydango.menu.MenuScreen
import com.bhst.dailydango.menu_api.MenuRoute
import com.bhst.dailydango.route_api.Route

fun EntryProviderScope<Route>.menuEntries(

) {
    entry<MenuRoute> {
        MenuScreen()
    }
}