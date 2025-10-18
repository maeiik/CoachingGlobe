import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import ch.coachingglobe.Kuerzel
import ch.coachingglobe.UserDto
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

val url = "https://lastingadventures.com/wp-content/uploads/2020/08/El-cap-facts.jpeg"

@Composable
fun GCImage(photoUrl: String, modifier: Modifier = Modifier) {
//    KamelImage(
//        resource = { asyncPainterResource(url) },
//        contentDescription = "Profile photo",
//        contentScale = ContentScale.Crop,
//        onFailure = { Text(text = "Image failed to load") },
//        modifier = modifier
//    )
    Kuerzel(photoUrl)
}

@Composable
fun GCImage(user: UserDto, modifier: Modifier = Modifier) {
    if (user.photoUrl != null && false) {
        KamelImage(
            resource = { asyncPainterResource(url) },
            contentDescription = "Profile photo",
            contentScale = ContentScale.Crop,
            onFailure = { Text(text = "Image failed to load") },
            modifier = modifier
        )
    } else {
        Kuerzel(user)
    }
}