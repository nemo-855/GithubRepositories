package com.nemo.compose_ui.designsystem

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubScaffold(
    modifier: Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            // FIXME Implement
        },
        topBar = { topBar() },
        bottomBar = { bottomBar() },
    ) { insetPadding ->
        content(insetPadding)
    }
}
