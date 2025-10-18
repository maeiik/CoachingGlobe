package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class CoachableSet(
    val id: String,
    val title: String,
    val description: String? = null,
    val nuggets: List<Nugget>,
)

@Serializable
data class CoachableSetNav(val id: String)