package ch.coachingglobe


data class NuggetDto(
    val id: String,
    val title: String,
    val youtubeUrl: String,
    val description: String? = null,
    val author: AuthorDto
)