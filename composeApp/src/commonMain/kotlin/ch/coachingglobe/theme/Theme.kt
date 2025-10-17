package ch.coachingglobe.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0066CC),
    onPrimary = Color.Companion.White,
    secondary = Color(0xFF00A896),
    onSecondary = Color.Companion.White,
    background = Color(0xFFF6F8FB),
    onBackground = Color(0xFF101828),
    surface = Color.Companion.White,
    onSurface = Color(0xFF101828),
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(), // default typography; customize if needed
        shapes = Shapes(),
        content = content
    )
}