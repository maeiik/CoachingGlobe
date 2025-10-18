package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class CoachableSetDto(
    val id: String,
    val title: String,
    val description: String? = null,
    val nuggets: List<NuggetDto>,
)

@Serializable
data class CoachableSetNav(val id: String)