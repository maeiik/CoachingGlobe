package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val id: Int,
    val user: UserDto,
    val coachableSet: CoachableSet,
    val status: String // E.g., "pending", "accepted", "rejected"
)