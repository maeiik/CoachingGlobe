package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class SubjectDto(
    val id: Int,
    val title: String,
    val description: String,
    val subjects: List<Int>? = null,
    val coachableSets: List<Int>? = null
)

@Serializable
data class CoachableSetDto(
    val id: Int,
    val title: String,
    val description: String,
    val nuggets: List<Int>,
    val author: Int,
)

@Serializable
data class NuggetDto(
    val id: Int,
    val title: String,
    val youtubeUrl: String? = null,
    val description: String,
    val author: Int,
)

@Serializable
data class AuthorDto(
    val user: Int,
    val descr: String,
    val profession: String,
)

@Serializable
data class UserDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photoUrl: String? = null,
    val email: String = "",
) {
    val name = "$firstName $lastName"
}

@Serializable
data class RequestDto(
    val id: Int,
    val user: UserDto,
    val coachableSet: Int,
    val status: String // E.g., "pending", "accepted", "rejected"
)

@Serializable
data class RequestsDto(
    val requests: List<RequestDto>
)

@Serializable
data class MySpaceDto(
    val requestedCoachableSets: List<Int>,
    val enrolledCoachableSets: List<Int>
)

@Serializable
data class TrainerDto(
    val coachedSets: List<Int>,
    val requests: List<RequestDto>
)

@Serializable
data class DataDto(
    val subjects: List<SubjectDto>,
    val coachableSets: List<CoachableSetDto>,
    val nuggets: List<NuggetDto>,
    val authors: List<AuthorDto>,
)