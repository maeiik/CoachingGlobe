package ch.coachingglobe

import android.os.Build
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


@Composable
actual fun YouTubePlayer(videoId: String, modifier: Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                //loadUrl("https://www.youtube.com/embed/$videoId")
                loadUrl("https://www.youtube.com/watch?v=Scjycn8wFgE")
            }
        }
    )
}