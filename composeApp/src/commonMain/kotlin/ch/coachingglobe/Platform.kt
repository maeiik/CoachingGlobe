package ch.coachingglobe

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun YouTubePlayer(videoId: String, modifier: Modifier = Modifier)