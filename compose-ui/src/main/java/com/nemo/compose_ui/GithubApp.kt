package com.nemo.compose_ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nemo.compose_ui.designsystem.GithubTheme
import com.nemo.compose_ui.top.TopNavGraph
import com.nemo.compose_ui.top.topNavGraph

@Composable
fun GithubApp(
    githubExternalNavigationController: GithubExternalNavigationController =
        rememberGithubExternalNavigationController()
) {
    GithubTheme {
        NavHost(
            modifier = Modifier,
            navController = rememberNavController(),
            startDestination = TopNavGraph.topRoute
        ) {
            topNavGraph(onLinkClick = githubExternalNavigationController::navigate)
        }
    }
}

@Composable
fun rememberGithubExternalNavigationController(): GithubExternalNavigationController {
    val context = LocalContext.current

    return remember(context) {
        GithubExternalNavigationController(context)
    }
}

class GithubExternalNavigationController(
    private val context: Context
) {
    fun navigate(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}