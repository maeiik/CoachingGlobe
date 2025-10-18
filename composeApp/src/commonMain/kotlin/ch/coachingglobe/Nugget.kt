package ch.coachingglobe

import kotlinx.serialization.Serializable


@Serializable
data class Nugget(
    val id: Int = 1,
    val id_legacy: String,
    val title: String,
    val youtubeUrl: String? = null,
    val description: String,
    val author: Author,
)

@Serializable
data class NuggetNav(val id: Int)