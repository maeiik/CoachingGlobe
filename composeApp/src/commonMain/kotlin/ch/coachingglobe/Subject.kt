package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    val id: Int = 1,
    val id_legacy: String = "",
    val title: String,
    val description: String,
    val subjects: List<SubjectDto>? = null,
    val coachableSets: List<CoachableSetDto>? = null
)

@Serializable
class SubjectNav(val id: Int, val title: String)