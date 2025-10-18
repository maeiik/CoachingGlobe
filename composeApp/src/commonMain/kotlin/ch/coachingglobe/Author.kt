package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val user: UserDto,
    val descr: String = "I am passionate",
    val profession: String = "Coach",
)