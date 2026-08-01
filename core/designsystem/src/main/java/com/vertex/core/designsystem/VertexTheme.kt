package com.vertex.core.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkScheme = darkColorScheme(
    primary = Color(0xFF8EA7FF),
    secondary = Color(0xFF73E2A7),
    background = Color(0xFF05060A),
    surface = Color(0xFF10131C),
)

private val LightScheme = lightColorScheme(
    primary = Color(0xFF3957D7),
    secondary = Color(0xFF087A45),
    background = Color(0xFFF7F8FF),
    surface = Color.White,
)

@Composable
fun VertexTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkScheme else LightScheme,
        typography = MaterialTheme.typography,
        content = content,
    )
}
