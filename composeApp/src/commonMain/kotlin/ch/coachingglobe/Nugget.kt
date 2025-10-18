package ch.coachingglobe

import kotlinx.serialization.Serializable


@Serializable
data class Nugget(
    val id: String,
    val title: String,
    val youtubeUrl: String? = null,
    val description: String,
    val author: Author,
)

@Serializable
data class NuggetNav(val id: String)