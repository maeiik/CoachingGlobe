package ch.coachingglobe


import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val firstName: String,
    val lastName: String,
    val photoUrl: String? = null,
    val bio: String? = null,
    val title: String? = null
)