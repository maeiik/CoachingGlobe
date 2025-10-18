package ch.coachingglobe

import kotlinx.serialization.Serializable


@Serializable
data class NuggetDto(
    val id: String,
    val title: String,
    val youtubeUrl: String? = null,
    val description: String? = null,
    val author: AuthorDto? = null,
)

@Serializable
data class NuggetNav(val id: String)