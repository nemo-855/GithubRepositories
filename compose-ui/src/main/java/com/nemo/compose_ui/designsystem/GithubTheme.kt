package com.nemo.compose_ui.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val lightColorPalette = lightColorScheme(
    primary = GithubColorPalette.Blue500,
    primaryContainer = GithubColorPalette.Blue700,
    secondary = GithubColorPalette.Yellow500,
    secondaryContainer = GithubColorPalette.Yellow700,
    background = GithubColorPalette.Grey50,
    surface = GithubColorPalette.Grey100,
    error = GithubColorPalette.Yellow400,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = GithubColorPalette.Grey900,
    onSurface = GithubColorPalette.Grey900,
    onError = Color.Black
)


@Composable
fun GithubTheme(content: @Composable () -> Unit) {
    // Currently only light themes are supported
    MaterialTheme(
        colorScheme = lightColorPalette,
        typography = GithubTypography,
        shapes = Shapes(),
        content = content,
    )
}