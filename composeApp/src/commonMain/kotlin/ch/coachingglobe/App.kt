package ch.coachingglobe


import androidx.compose.foundation.layout.*
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
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = GlobalCoachingNavigationGraph.ExploreGraph.Explore
                    ) {
                        composable<GlobalCoachingNavigationGraph.ExploreGraph.Explore> {
                            ExploreScreen { navController.navigate(SubjectNav("coaching_globe")) }

                        }

                        composable<SubjectNav> {
                            val subject = it.toRoute<SubjectNav>()
                            SubjectScreen(
                                subject = dataViewModel.getSubject(subject.id)!!,
                                onBack = if (subject.id != "coaching_globe") {
                                    { navController.popBackStack() }
                                } else null,
                                onSubjectClicked = { subject ->
                                    navController.navigate(SubjectNav(subject.id))
                                },
                                onCoachableSetClicked = { coachableSet ->
                                    navController.navigate(CoachableSetNav(coachableSet.id))
                                }
                            )
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
                            MySpaceScreen()
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
                                onCouchMe = { /* handle action */ }
                            )
                        }
                    }
                }
            }
        }
    }
}
