package ch.coachingglobe


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ch.coachingglobe.ui.theme.AppTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

val LocalDataViewModel = staticCompositionLocalOf<DataViewModel> {
    error("No DataViewModel provided")
}

@Composable
fun App() {
    SetStatusBarColor()
    val dataViewModel = remember { DataViewModel() }
    CompositionLocalProvider(LocalDataViewModel provides dataViewModel) {
        AppTheme {
            val navController = rememberNavController()
            val items = GlobalCoachingNavigationGraph.items
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Scaffold(
                bottomBar = {
                    NavigationBar {
                        items.forEach { (item, icon) ->
                            val title = item::class.simpleName ?: "Unknown"
                            NavigationBarItem(
                                selected = currentRoute?.contains("${title}Graph") == true,
                                onClick = {
                                    navController.navigate(item) {
                                        navController.graph.startDestinationRoute?.let {
                                            popUpTo(it) { saveState = true }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = { Icon(icon, contentDescription = title) },
                                label = { Text(title) }
                            )
                        }
                    }
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = GlobalCoachingNavigationGraph.ExploreGraph.Explore
                    ) {
                        composable<GlobalCoachingNavigationGraph.ExploreGraph.Explore> {
                            SubjectScreenWithSubjectsWithLoading(
                                "GlobalCoaching",
                                dataViewModel.dataResponse.collectAsState().value,
                                onBack = null,
                                onSubjectClicked = { subject ->
                                    navController.navigate(
                                        SubjectNav(
                                            subject.id,
                                            subject.id_legacy
                                        )
                                    )
                                },
                            )
                        }

                        composable<SubjectNav> {
                            val subjectNav = it.toRoute<SubjectNav>()
                            val subject = dataViewModel.getSubject(subjectNav.id)
                            if (subject?.subjects?.isNotEmpty() == true) {
                                SubjectListScreen(
                                    subject.title,
                                    subject.subjects.map { it.toSubject(dataViewModel.data.value!!) },
                                    onBack = { navController.popBackStack() },
                                    onSubjectClicked = { subject ->
                                        navController.navigate(
                                            SubjectNav(
                                                subject.id,
                                                subject.id_legacy
                                            )
                                        )
                                    },
                                )
                            } else if (subject?.coachableSets != null) {
                                CoachableSetListScreen(
                                    title = subject.title,
                                    coachableSetDto = subject.coachableSets.map {
                                        it.toCoachableSet(
                                            dataViewModel.data.value!!
                                        )
                                    },
                                    onBack = if (subject.id != 1) {
                                        { navController.popBackStack() }
                                    } else null,
                                    onCoachableSetClicked = { subject ->
                                        navController.navigate(
                                            CoachableSetNav(subject.id)
                                        )
                                    },
                                )
                            }
                        }

                        composable<CoachableSetNav> {
                            CoachableSetScreen(
                                id = it.toRoute<CoachableSetNav>().id,
                                onBack = navController::popBackStack,
                                onOpen = { nugget ->
                                    navController.navigate(NuggetNav(nugget.id))
                                }
                            )
                        }

                        composable<GlobalCoachingNavigationGraph.MySpaceGraph.MySpace> {
                            MySpaceScreen(navController)
                        }

                        composable<GlobalCoachingNavigationGraph.TrainerGraph.Trainer> {
                            TrainerScreen(navController)
                        }

                        composable<GlobalCoachingNavigationGraph.ProfileGraph.Profile> {
                            ProfileScreen(
                                profile = ProfileDto(
                                    firstName = "John",
                                    lastName = "Doe",
                                    location = "Earth",
                                    photoUrl = null
                                )
                            )
                        }

                        composable<NuggetNav> {
                            val nugget = it.toRoute<NuggetNav>()
                            NuggetScreen(
                                nugget = dataViewModel.getNugget(nugget.id),
                                onBack = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GCScaffold(
    title: String,
    onBack: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    onBack?.let {
                        IconButton(onClick = it) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            content.invoke()
        }
    }
}