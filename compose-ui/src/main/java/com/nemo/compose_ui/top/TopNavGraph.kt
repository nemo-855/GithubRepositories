package com.nemo.compose_ui.top

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.topNavGraph(onLinkClick: (String) -> Unit) {
    composable(route = TopNavGraph.topRoute) {
        TopScreen(onLinkClick = onLinkClick)
    }
}

object TopNavGraph {
    const val topRoute = "top"
}