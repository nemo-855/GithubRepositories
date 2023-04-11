package com.nemo.compose_ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nemo.compose_ui.designsystem.GithubTheme
import com.nemo.compose_ui.top.TopNavGraph
import com.nemo.compose_ui.top.topNavGraph

@Composable
fun GithubApp() {
    GithubTheme {
        NavHost(
            modifier = Modifier,
            navController = rememberNavController(),
            startDestination = TopNavGraph.topRoute
        ) {
            topNavGraph()
        }
    }
}
