package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val id: Int,
    val user: UserDto,
    val nugget: Nugget,
    val status: String? = null // E.g., "pending", "accepted", "rejected"
)