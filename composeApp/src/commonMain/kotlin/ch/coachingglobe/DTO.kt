package ch.coachingglobe

import kotlinx.serialization.Serializable

@Serializable
data class SubjectDto(
    val id: Int,
    val title: String,
    val description: String,
    val subjects: List<Int>? = null,
    val coachableSets: List<Int>? = null
) {
    fun toSubject(data: DataDto) = Subject(
        id = id,
        title = title,
        description = description ?: "",
        subjects = data.subjects.filter { subjects?.contains(it.id) == true },
        coachableSets = data.coachableSets.filter { coachableSets?.contains(it.id) == true },
    )
}

@Serializable
data class CoachableSetDto(
    val id: Int,
    val title: String,
    val description: String? = null,
    val nuggets: List<Int>,
    val author: Int,
) {
    fun toCoachableSet(data: DataDto) = CoachableSet(
        id = id,
        ic_legacy = id.toString(),
        title = title,
        description = description,
        nuggets = data.nuggets.filter { nuggets.contains(it.id) }.map { it.toNugget(data) },
        author = data.authors.firstOrNull { it.user.id == author }?.toAuthor(data) ?: exampleAuthor,
    )
}

val exampleAuthor = Author(
    user = UserDto(0, "First", "Last"),
    descr = "No description",
    profession = "No profession"
)

@Serializable
data class NuggetDto(
    val id: Int,
    val title: String,
    val youtubeUrl: String? = null,
    val description: String = "",
    val author: Int,
) {
    fun toNugget(data: DataDto) = Nugget(
        id = id,
        id_legacy = id.toString(),
        title = title,
        youtubeUrl = youtubeUrl,
        description = description,
        author = data.authors.firstOrNull { it.user.id == author }?.toAuthor(data) ?: exampleAuthor,
    )
}

@Serializable
data class AuthorDto(
    val user: UserDto = UserDto(0, "First", "Last"),
    val descr: String,
    val profession: String,
) {
    fun toAuthor(data: DataDto) = Author(
        user = user,
        descr = descr,
        profession = profession,
    )
}

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