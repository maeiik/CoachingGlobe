package ch.coachingglobe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlin.collections.plus

class DataViewModel : ViewModel() {
    val sampleNuggets: List<Nugget> = listOf(
        Nugget(
            id = "n1",
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
            id = "n2",
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
            id = "n3",
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
            id = "n4",
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
            id = "cs1",
            title = "Productivity Boosters",
            description = "Nuggets to enhance your productivity at work.",
            nuggets = sampleNuggets.filter { it.id in listOf("n1", "n3") }
        ),
        CoachableSet(
            id = "cs2",
            title = "Wellness at Work",
            description = "Nuggets focused on mindfulness and physical well-being.",
            nuggets = sampleNuggets.filter { it.id in listOf("n2", "n4") }
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

    private val myNuggetsMutable = MutableStateFlow<Map<String, NuggetWithStatus>>(emptyMap())
    val myNuggets = myNuggetsMutable.asStateFlow()

    private fun updateMyNuggets(nuggetId: String, status: NuggetStatus) {
        myNuggetsMutable.value =
            myNuggetsMutable.value + (nuggetId to myNuggetsMutable.value.getOrElse(
                nuggetId,
                { NuggetWithStatus(getNugget(nuggetId), NuggetStatus.NONE) }
            ).copy(status = status)
                    )
    }

    fun getNugget(id: String): Nugget {
        return sampleNuggets.find { it.id == id } ?: sampleNuggets.first()
    }

    fun getCoachableSetById(id: String): CoachableSet {
        return sampleCoachableSets.find { it.id == id } ?: sampleCoachableSets.first()
    }

    fun getAllCoachableSets() = sampleCoachableSets

    fun getSubject(id: String, subjectToSearch: Subject = subjectExamples): Subject? {
        if (subjectToSearch.id == id) return subjectToSearch
        for (subject in subjectToSearch.subjects ?: emptyList()) {
            if (subject.id == id) return subject
        }
        return null
    }

    fun getCoachableSet(
        id: String,
        subjectToSearch: Subject = subjectExamples
    ): CoachableSet? {
        subjectToSearch.coachableSets?.firstOrNull { it.id == id }?.let { return it }
        for (subject in subjectToSearch.subjects ?: emptyList()) {
            val result = getCoachableSet(id, subject)
            if (result != null) return result
        }
        return null
    }

    fun onCouchMeClicked(nuggetId: String) {
        updateMyNuggets(nuggetId, NuggetStatus.REQUESTED)
    }

    fun onAcceptClicked(nuggetId: String) {
        updateMyNuggets(nuggetId, NuggetStatus.COACHING)
    }

    fun revertRequest(nuggetId: String) {
        updateMyNuggets(nuggetId, NuggetStatus.NONE)
    }
}


val request1 = RequestDto(
    id = "1",
    user = UserDto(
        id = 1,
        firstName = "John",
        lastName = "Doe",
        photoUrl = "https://example.com/images/john_doe.jpg",
        email = "john.doe@example.com"
    ),
    coachableSet = CoachableSet(
        id = "1",
        title = "Leadership Coaching",
        description = "An in-depth course on leadership skills for managers.",
        nuggets = listOf(
            Nugget(
                id = "1",
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
                id = "2",
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
        )
    ),
    status = "pending"
)

val request2 = RequestDto(
    id = "2",
    user = UserDto(
        id = 2,
        firstName = "Alice",
        lastName = "Johnson",
        photoUrl = "https://example.com/images/alice_johnson.jpg",
        email = "alice.johnson@example.com"
    ),
    coachableSet = CoachableSet(
        id = "2",
        title = "Stress Management",
        description = "Techniques and strategies to manage stress effectively.",
        nuggets = listOf(
            Nugget(
                id = "3",
                title = "Breathing Exercises",
                youtubeUrl = "https://youtube.com/video3",
                description = "Practice deep breathing exercises to relieve stress.",
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
                id = "4",
                title = "Mindfulness",
                youtubeUrl = "https://youtube.com/video4",
                description = "Learn mindfulness techniques to stay calm and focused.",
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
        )
    ),
    status = "accepted"
)

val request3 = RequestDto(
    id = "3",
    user = UserDto(
        id = 3,
        firstName = "Bob",
        lastName = "Williams",
        photoUrl = "https://example.com/images/bob_williams.jpg",
        email = "bob.williams@example.com"
    ),
    coachableSet = CoachableSet(
        id = "3",
        title = "Time Management",
        description = "Learn how to manage your time efficiently and achieve your goals.",
        nuggets = listOf(
            Nugget(
                id = "5",
                title = "Prioritizing Tasks",
                youtubeUrl = "https://youtube.com/video5",
                description = "Learn how to prioritize tasks effectively to maximize productivity.",
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
                id = "6",
                title = "Time Blocking",
                youtubeUrl = "https://youtube.com/video6",
                description = "Master the time-blocking technique to stay focused and on task.",
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
        )
    ),
    status = "rejected"
)

val request4 = RequestDto(
    id = "4",
    user = UserDto(
        id = 4,
        firstName = "Emma",
        lastName = "Taylor",
        photoUrl = "https://example.com/images/emma_taylor.jpg",
        email = "emma.taylor@example.com"
    ),
    coachableSet = CoachableSet(
        id = "4",
        title = "Public Speaking",
        description = "Improve your speaking skills for presentations and public engagements.",
        nuggets = listOf(
            Nugget(
                id = "7",
                title = "Overcoming Stage Fright",
                youtubeUrl = "https://youtube.com/video7",
                description = "Techniques to conquer your fear of public speaking.",
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
                id = "8",
                title = "Engaging Your Audience",
                youtubeUrl = "https://youtube.com/video8",
                description = "How to engage and maintain the interest of your audience during a speech.",
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
        )
    ),
    status = "pending"
)

val sampleRequests = listOf(request1, request2, request3, request4)