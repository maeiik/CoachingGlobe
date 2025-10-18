package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class SubjectDto(
    val id: String,
    val title: String,
    val description: String,
    val subjects: List<SubjectDto>? = null,
    val coachableSets: List<CoachableSetDto>? = null
)

@Serializable
class SubjectNav(val id: String)