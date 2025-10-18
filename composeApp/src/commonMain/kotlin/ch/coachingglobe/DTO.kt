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
    val description: String? = null,
    val nuggets: List<Int>,
)

@Serializable
data class NuggetDto(
    val id: String,
    val title: String,
    val youtubeUrl: String? = null,
    val description: String? = null,
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
    val id: String,
    val firstName: String,
    val lastName: String,
    val photoUrl: String? = null,
    val email: String,
)

@Serializable
data class RequestDto(
    val id: String,
    val user: UserDto,
    val coachableSet: CoachableSet,
    val status: String // E.g., "pending", "accepted", "rejected"
)

@Serializable
data class RequestsDto(
    val requests: List<RequestDto>
)