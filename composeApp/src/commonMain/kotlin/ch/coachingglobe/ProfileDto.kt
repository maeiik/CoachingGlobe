package ch.coachingglobe

data class ProfileDto(
    val firstName: String,
    val lastName: String,
    val photoUrl: String? = null,
    val bio: String? = null,
    val hobbies: List<String> = emptyList(),
    val location: String? = null
)