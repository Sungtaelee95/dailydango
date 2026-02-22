package com.bhst.dailydango.search.entry

import androidx.navigation3.runtime.EntryProviderScope
import com.bhst.dailydango.route_api.Route
import com.bhst.dailydango.search.screen.SearchScreen
import com.bhst.dailydango.search_api.SearchRoute

fun EntryProviderScope<Route>.searchEntries(

) {
    entry<SearchRoute> {
        SearchScreen()
    }
}