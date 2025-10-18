package ch.coachingglobe

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import kotlinx.serialization.Serializable


@Serializable
sealed interface GlobalCoachingNavigationGraph {

    companion object {
        val items = mapOf(
            ExploreGraph.Explore to Icons.Filled.Place,
            MySpaceGraph.MySpace to Icons.Filled.CheckCircle,
            ProfileGraph.Profile to Icons.Filled.Person,
        )
    }

    @Serializable
    sealed class ExploreGraph : GlobalCoachingNavigationGraph {
        @Serializable
        data object Explore : ExploreGraph()

        @Serializable
        data class ExploreSubject(val id: String) : ExploreGraph()

        @Serializable
        data class ExploreCoachableSet(val id: String) : ExploreGraph()

        @Serializable
        data class ExploreNugget(val id: String) : ExploreGraph()
    }


    @Serializable
    sealed class MySpaceGraph : GlobalCoachingNavigationGraph {
        @Serializable
        data object MySpace : MySpaceGraph()
    }


    @Serializable
    sealed class TrainerGraph : GlobalCoachingNavigationGraph {
        @Serializable
        data object Trainer : TrainerGraph()
    }

    @Serializable
    sealed class ProfileGraph : GlobalCoachingNavigationGraph {
        @Serializable
        data object Profile : ProfileGraph()
    }
}



