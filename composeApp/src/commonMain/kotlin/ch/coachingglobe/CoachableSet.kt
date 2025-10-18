package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class CoachableSet(
    val id: Int = 1,
    val ic_legacy: String,
    val title: String,
    val description: String? = null,
    val nuggets: List<Nugget>,
    val author: Author,
)

@Serializable
data class CoachableSetNav(val id: Int)