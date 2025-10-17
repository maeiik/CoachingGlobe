package ch.coachingglobe

import androidx.compose.runtime.Composable

@Composable
fun ExploreScreen() = ScreenLabel("Explore") {
    NuggetScreen(
        nugget = NuggetDto(
            id = "1",
            title = "Sample Nugget",
            youtubeUrl = "https://www.youtube.com/watch?v=Slawb3xlxo0",
            description = "This is a sample nugget description.",
            author = AuthorDto(
                firstName = "Jane",
                lastName = "Doe",
                photoUrl = null,
                bio = "An experienced coach.",
                title = "Senior Coach"
            )
        ),
        onBack = {},
        onCouchMe = {}
    )
}