package com.nemo.compose_ui.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColorScheme(
    primary = ColorPalette.Teal500,
    primaryContainer = ColorPalette.Teal700,
    secondary = ColorPalette.Yellow500,
    secondaryContainer = ColorPalette.Yellow700,
    background = ColorPalette.BlueGrey900,
    surface = ColorPalette.BlueGrey800,
    error = ColorPalette.Yellow400,
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
        typography = Typography(),
        shapes = Shapes(),
        content = content,
    )
}