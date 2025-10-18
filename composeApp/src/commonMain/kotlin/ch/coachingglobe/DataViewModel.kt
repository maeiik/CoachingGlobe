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

//    val sampleCoachableSets: List<CoachableSet> = listOf(
//        CoachableSet(
//            ic_legacy = "1",
//            title = "Productivity Boosters",
//            description = "Nuggets to enhance your productivity at work.",
//            nuggets = sampleNuggets,
//            author = author
//        ),
//        CoachableSet(
//            ic_legacy = "1",
//            title = "Wellness at Work",
//            description = "Nuggets focused on mindfulness and physical well-being.",
//            nuggets = sampleNuggets.take(2),
//            author = author
//        )
//    )

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

    val apiService = ApiService()

    val dataResponse = ResponseX<DataDto>()

    private val dataMutable = MutableStateFlow<DataDto?>(null)
    val data = dataMutable.asStateFlow()

    private val myNuggetsMutable = MutableStateFlow<Map<Int, NuggetWithStatus>>(emptyMap())
    val myNuggets = myNuggetsMutable.asStateFlow()

    init {
        loadData()
    }

    fun loadData(forceReload: Boolean = false) {
        handleRequest(
            response = dataResponse,
            name = "loadMyGroups",
            onSuccessKeepState = {
                dataMutable.value = it
                println("UBMB: data loaded: ${it}")
            }
        ) {
            apiService.loadData()
        }
    }

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

    fun getSubject(id: Int): Subject? {
        val data = data.value ?: return null
        return data.subjects.firstOrNull { it.id == id }?.toSubject(data)
    }

    fun getCoachableSet(id: Int): CoachableSet? {
        val data = data.value ?: return null
        return data.coachableSets.firstOrNull { it.id == id }?.toCoachableSet(data)
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

//val c1 = CoachableSet(
//    ic_legacy = "1",
//    title = "Leadership Coaching",
//    description = "An in-depth course on leadership skills for managers.",
//    nuggets = listOf(
//        Nugget(
//            id_legacy = "1",
//            title = "Goal Setting",
//            youtubeUrl = "https://youtube.com/video1",
//            description = "Learn to set clear, measurable goals for effective leadership.",
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
//            id_legacy = "1",
//            title = "Effective Communication",
//            youtubeUrl = "https://youtube.com/video2",
//            description = "Master the art of communicating with clarity and confidence.",
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
//    ),
//    author = Author(
//        UserDto(
//            id = 2,
//            firstName = "Alice",
//            lastName = "Johnson",
//            photoUrl = "https://example.com/images/alice_johnson.jpg",
//            email = ""
//        )
//    )
//)
//
//
//val request1 = Request(
//    id = 1,
//    user = UserDto(
//        id = 1,
//        firstName = "John",
//        lastName = "Doe",
//        photoUrl = "https://example.com/images/john_doe.jpg",
//        email = "john.doe@example.com"
//    ),
//    coachableSet = c1,
//    status = "pending"
//)
//
//val sampleRequests = listOf(request1, request1, request1)