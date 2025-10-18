package ch.coachingglobe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.collections.plus

class DataViewModel : ViewModel() {
    val sampleNuggets: List<Nugget> = listOf(
        Nugget(
            id_legacy = "1",
            title = "Quick tips for better focus",
            youtubeUrl = "https://youtu.be/dQw4w9WgXcQ",
            description = "Short techniques to improve concentration and productivity.",
            author = Author(
                user = UserDto(
                    id = 33,
                    firstName = "Sara",
                    lastName = "Keller",
                    photoUrl = "https://example.com/photos/sara.jpg"
                ),
            )
        ),
        Nugget(
            id_legacy = "1",
            title = "Mindful breathing exercise",
            youtubeUrl = "https://www.youtube.com/watch?v=od8Fv0q3K3g",
            description = "A 5-minute guided breathing practice to calm the mind.",
            author = Author(
                user = UserDto(
                    id = 33,
                    firstName = "Sara",
                    lastName = "Keller",
                    photoUrl = "https://example.com/photos/sara.jpg"
                ),
            )
        ),
        Nugget(
            id_legacy = "1",
            title = "How to give better feedback",
            youtubeUrl = "https://youtu.be/9bZkp7q19f0",
            description = "Concrete patterns for giving constructive feedback in teams.",
            author = Author(
                user = UserDto(
                    id = 33,
                    firstName = "Sara",
                    lastName = "Keller",
                    photoUrl = "https://example.com/photos/sara.jpg"
                ),
            )
        ),
        Nugget(
            id_legacy = "1",
            title = "Quick posture reset",
            youtubeUrl = "https://www.youtube.com/embed/3JZ_D3ELwOQ",
            description = "Simple movements to correct posture at your desk.",
            author = Author(
                user = UserDto(
                    id = 33,
                    firstName = "Sara",
                    lastName = "Keller",
                    photoUrl = "https://example.com/photos/sara.jpg"
                ),
            )
        )
    )

    val sampleCoachableSets: List<CoachableSet> = listOf(
        CoachableSet(
            ic_legacy = "1",
            title = "Productivity Boosters",
            description = "Nuggets to enhance your productivity at work.",
            nuggets = sampleNuggets,
            author = author
        ),
        CoachableSet(
            ic_legacy = "1",
            title = "Wellness at Work",
            description = "Nuggets focused on mindfulness and physical well-being.",
            nuggets = sampleNuggets.take(2),
            author = author
        )
    )

    enum class NuggetStatus {
        NONE,
        REQUESTED,
        COACHING,
        FINISHED,
    }

    data class NuggetWithStatus(
        val nugget: Nugget,
        val status: NuggetStatus,
    )

    fun loadMyGroups(forceReload: Boolean = false) {
        handleRequest(response = groups, name = "loadMyGroups") {
            apiService.loadMyGroups(forceReload = forceReload).groups
        }
    }

    private val dataResponse = ResponseX<DataDto>()

    private val myNuggetsMutable = MutableStateFlow<Map<Int, NuggetWithStatus>>(emptyMap())
    val myNuggets = myNuggetsMutable.asStateFlow()

    private fun updateMyNuggets(nuggetId: Int, status: NuggetStatus) {
        myNuggetsMutable.value =
            myNuggetsMutable.value + (nuggetId to myNuggetsMutable.value.getOrElse(
                nuggetId,
                { NuggetWithStatus(getNugget(nuggetId), NuggetStatus.NONE) }
            ).copy(status = status)
                    )
    }

    fun getNugget(id: Int): Nugget {
        return sampleNuggets.find { it.id == id } ?: sampleNuggets.first()
    }

    fun getCoachableSetById(id: String): CoachableSet {
        return sampleCoachableSets.find { it.ic_legacy == id } ?: sampleCoachableSets.first()
    }

    fun getAllCoachableSets() = sampleCoachableSets

    fun getSubject(id: Int, subjectToSearch: Subject = subjectExamples): Subject? {
        if (subjectToSearch.id == id) return subjectToSearch
        for (subject in subjectToSearch.subjects ?: emptyList()) {
            if (subject.id == id) return subject
        }
        return null
    }

    fun getCoachableSet(
        id: Int,
        subjectToSearch: Subject = subjectExamples
    ): CoachableSet? {
        subjectToSearch.coachableSets?.firstOrNull { it.id == id }?.let { return it }
        for (subject in subjectToSearch.subjects ?: emptyList()) {
            val result = getCoachableSet(id, subject)
            if (result != null) return result
        }
        return null
    }

    fun onCouchMeClicked(nuggetId: Int) {
        updateMyNuggets(nuggetId, NuggetStatus.REQUESTED)
    }

    fun onAcceptClicked(nuggetId: Int) {
        updateMyNuggets(nuggetId, NuggetStatus.COACHING)
    }

    fun revertRequest(nuggetId: Int) {
        updateMyNuggets(nuggetId, NuggetStatus.NONE)
    }
}

val c1 = CoachableSet(
    ic_legacy = "1",
    title = "Leadership Coaching",
    description = "An in-depth course on leadership skills for managers.",
    nuggets = listOf(
        Nugget(
            id_legacy = "1",
            title = "Goal Setting",
            youtubeUrl = "https://youtube.com/video1",
            description = "Learn to set clear, measurable goals for effective leadership.",
            author = Author(
                UserDto(
                    id = 2,
                    firstName = "Alice",
                    lastName = "Johnson",
                    photoUrl = "https://example.com/images/alice_johnson.jpg",
                    email = "alice.johnson@example.com"
                )
            )
        ),
        Nugget(
            id_legacy = "1",
            title = "Effective Communication",
            youtubeUrl = "https://youtube.com/video2",
            description = "Master the art of communicating with clarity and confidence.",
            author = Author(
                UserDto(
                    id = 2,
                    firstName = "Alice",
                    lastName = "Johnson",
                    photoUrl = "https://example.com/images/alice_johnson.jpg",
                    email = "alice.johnson@example.com"
                )
            )
        )
    ),
    author = Author(
        UserDto(
            id = 2,
            firstName = "Alice",
            lastName = "Johnson",
            photoUrl = "https://example.com/images/alice_johnson.jpg",
            email = ""
        )
    )
)


val request1 = Request(
    id = 1,
    user = UserDto(
        id = 1,
        firstName = "John",
        lastName = "Doe",
        photoUrl = "https://example.com/images/john_doe.jpg",
        email = "john.doe@example.com"
    ),
    coachableSet = c1,
    status = "pending"
)

//val c2 = CoachableSet(
//    id = "2",
//    title = "Stress Management",
//    description = "Techniques and strategies to manage stress effectively.",
//    nuggets = listOf(
//        Nugget(
//            id = "3",
//            title = "Breathing Exercises",
//            youtubeUrl = "https://youtube.com/video3",
//            description = "Practice deep breathing exercises to relieve stress.",
//            author = Author(
//                UserDto(
//                    id = 2,
//                    firstName = "Alice",
//                    lastName = "Johnson",
//                    photoUrl = "https://example.com/images/alice_johnson.jpg",
//                    email = "alice.johnson@example.com"
//                )
//            )
//        ),
//        Nugget(
//            id = "4",
//            title = "Mindfulness",
//            youtubeUrl = "https://youtube.com/video4",
//            description = "Learn mindfulness techniques to stay calm and focused.",
//            author = Author(
//                UserDto(
//                    id = 2,
//                    firstName = "Alice",
//                    lastName = "Johnson",
//                    photoUrl = "https://example.com/images/alice_johnson.jpg",
//                    email = "alice.johnson@example.com"
//                )
//            )
//        )
//    )
//)
//val request2 = RequestDto(
//    id = "2",
//    user = UserDto(
//        id = 2,
//        firstName = "Alice",
//        lastName = "Johnson",
//        photoUrl = "https://example.com/images/alice_johnson.jpg",
//        email = "alice.johnson@example.com"
//    ),
//    coachableSet = 2,
//    status = "accepted"
//)
//
//val c3 = CoachableSet(
//    id = "3",
//    title = "Time Management",
//    description = "Learn how to manage your time efficiently and achieve your goals.",
//    nuggets = listOf(
//        Nugget(
//            id = "5",
//            title = "Prioritizing Tasks",
//            youtubeUrl = "https://youtube.com/video5",
//            description = "Learn how to prioritize tasks effectively to maximize productivity.",
//            author = Author(
//                UserDto(
//                    id = 2,
//                    firstName = "Alice",
//                    lastName = "Johnson",
//                    photoUrl = "https://example.com/images/alice_johnson.jpg",
//                    email = "alice.johnson@example.com"
//                )
//            )
//        ),
//        Nugget(
//            id = "6",
//            title = "Time Blocking",
//            youtubeUrl = "https://youtube.com/video6",
//            description = "Master the time-blocking technique to stay focused and on task.",
//            author = Author(
//                UserDto(
//                    id = 2,
//                    firstName = "Alice",
//                    lastName = "Johnson",
//                    photoUrl = "https://example.com/images/alice_johnson.jpg",
//                    email = "alice.johnson@example.com"
//                )
//            )
//        )
//    )
//)
//
//val request3 = RequestDto(
//    id = "3",
//    user = UserDto(
//        id = 3,
//        firstName = "Bob",
//        lastName = "Williams",
//        photoUrl = "https://example.com/images/bob_williams.jpg",
//        email = "bob.williams@example.com"
//    ),
//    coachableSet = 3,
//    status = "rejected"
//)
//
//val c4 = CoachableSet(
//    id = "4",
//    title = "Public Speaking",
//    description = "Improve your speaking skills for presentations and public engagements.",
//    nuggets = listOf(
//        Nugget(
//            id = "7",
//            title = "Overcoming Stage Fright",
//            youtubeUrl = "https://youtube.com/video7",
//            description = "Techniques to conquer your fear of public speaking.",
//            author = Author(
//                UserDto(
//                    id = 2,
//                    firstName = "Alice",
//                    lastName = "Johnson",
//                    photoUrl = "https://example.com/images/alice_johnson.jpg",
//                    email = "alice.johnson@example.com"
//                )
//            )
//        ),
//        Nugget(
//            id = "8",
//            title = "Engaging Your Audience",
//            youtubeUrl = "https://youtube.com/video8",
//            description = "How to engage and maintain the interest of your audience during a speech.",
//            author = Author(
//                UserDto(
//                    id = 2,
//                    firstName = "Alice",
//                    lastName = "Johnson",
//                    photoUrl = "https://example.com/images/alice_johnson.jpg",
//                    email = "alice.johnson@example.com"
//                )
//            )
//        )
//    )
//)
//val request4 = RequestDto(
//    id = "4",
//    user = UserDto(
//        id = 4,
//        firstName = "Emma",
//        lastName = "Taylor",
//        photoUrl = "https://example.com/images/emma_taylor.jpg",
//        email = "emma.taylor@example.com"
//    ),
//    coachableSet = 4,
//    status = "pending"
//)

val sampleRequests = listOf(request1, request1, request1)