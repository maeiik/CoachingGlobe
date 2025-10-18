package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    val id: String,
    val title: String,
    val description: String,
    val subjects: List<Subject>? = null,
    val coachableSets: List<CoachableSet>? = null
)

@Serializable
class SubjectNav(val id: String)