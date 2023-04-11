package com.nemo.compose_ui.top

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.topNavGraph() {
    composable(route = TopNavGraph.topRoute) {
        TopScreen()
    }
}

object TopNavGraph {
    const val topRoute = "top"
}