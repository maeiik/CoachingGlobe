package ch.coachingglobe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.collections.plus

class DataViewModel : ViewModel() {
    val sampleNuggets: List<NuggetDto> = listOf(
        NuggetDto(
            id = "n1",
            title = "Quick tips for better focus",
            youtubeUrl = "https://youtu.be/dQw4w9WgXcQ",
            description = "Short techniques to improve concentration and productivity.",
            author = AuthorDto(
                firstName = "Alex",
                lastName = "Müller",
                photoUrl = null,
                bio = "Productivity coach helping teams work smarter.",
                title = "Productivity Coach"
            )
        ),
        NuggetDto(
            id = "n2",
            title = "Mindful breathing exercise",
            youtubeUrl = "https://www.youtube.com/watch?v=od8Fv0q3K3g",
            description = "A 5-minute guided breathing practice to calm the mind.",
            author = AuthorDto(
                firstName = "Sara",
                lastName = "Keller",
                photoUrl = "https://example.com/photos/sara.jpg",
                bio = "Mindfulness instructor and trainer.",
                title = "Mindfulness Instructor"
            )
        ),
        NuggetDto(
            id = "n3",
            title = "How to give better feedback",
            youtubeUrl = "https://youtu.be/9bZkp7q19f0",
            description = "Concrete patterns for giving constructive feedback in teams.",
            author = AuthorDto(
                firstName = "Luca",
                lastName = "Meier",
                photoUrl = null,
                bio = "Agile coach focused on communication and leadership.",
                title = "Agile Coach"
            )
        ),
        NuggetDto(
            id = "n4",
            title = "Quick posture reset",
            youtubeUrl = "https://www.youtube.com/embed/3JZ_D3ELwOQ",
            description = "Simple movements to correct posture at your desk.",
            author = AuthorDto(
                firstName = "Nina",
                lastName = "Fischer",
                photoUrl = "https://example.com/photos/nina.png",
                bio = "Physiotherapist specialized in workplace ergonomics.",
                title = "Physiotherapist"
            )
        )
    )

    val sampleCoachableSets: List<CoachableSetDto> = listOf(
        CoachableSetDto(
            id = "cs1",
            title = "Productivity Boosters",
            description = "Nuggets to enhance your productivity at work.",
            nuggets = sampleNuggets.filter { it.id in listOf("n1", "n3") }
        ),
        CoachableSetDto(
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
        val nugget: NuggetDto,
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

    fun getNugget(id: String): NuggetDto {
        return sampleNuggets.find { it.id == id } ?: sampleNuggets.first()
    }

    fun getCoachableSetById(id: String): CoachableSetDto {
        return sampleCoachableSets.find { it.id == id } ?: sampleCoachableSets.first()
    }

    fun getAllCoachableSets() = sampleCoachableSets

    fun getSubject(id: String, subjectToSearch: SubjectDto = subjectExamples): SubjectDto? {
        if (subjectToSearch.id == id) return subjectToSearch
        for (subject in subjectToSearch.subjects ?: emptyList()) {
            if (subject.id == id) return subject
        }
        return null
    }

    fun getCoachableSet(
        id: String,
        subjectToSearch: SubjectDto = subjectExamples
    ): CoachableSetDto? {
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