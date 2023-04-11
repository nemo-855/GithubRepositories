package com.nemo.compose_ui.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColorScheme(
    primary = GithubColorPalette.Teal500,
    primaryContainer = GithubColorPalette.Teal700,
    secondary = GithubColorPalette.Yellow500,
    secondaryContainer = GithubColorPalette.Yellow700,
    background = GithubColorPalette.BlueGrey900,
    surface = GithubColorPalette.BlueGrey800,
    error = GithubColorPalette.Yellow400,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)


@Composable
fun GithubTheme(content: @Composable () -> Unit) {
    // Currently only dark themes are supported
    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = GithubTypography,
        shapes = Shapes(),
        content = content,
    )
}