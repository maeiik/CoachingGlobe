package ch.coachingglobe


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
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

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Explore : BottomNavItem("explore", "Explore", Icons.Filled.Place)
    object MySpace : BottomNavItem("myspace", "My Space", Icons.Filled.CheckCircle)
    object Profile : BottomNavItem("profile", "Profile", Icons.Filled.Person)
}

private object Routes {
    const val Explore = "explore" // reuse Explore as nuggets list
    const val NuggetDetailBase = "nugget"
    const val MySpace = "myspace"
    const val Profile = "profile"
}

val LocalDataViewModel = staticCompositionLocalOf<DataViewModel> {
    error("No DataViewModel provided")
}

@Composable
fun App() {
    val dataViewModel = remember { DataViewModel() }
    CompositionLocalProvider(LocalDataViewModel provides dataViewModel) {
        AppTheme {
            val navController = rememberNavController()
            val items = listOf(BottomNavItem.Explore, BottomNavItem.MySpace, BottomNavItem.Profile)
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Scaffold(
                bottomBar = {
                    NavigationBar {
                        items.forEach { item ->
                            val selected = when {
                                currentRoute == null -> false
                                currentRoute.startsWith(Routes.NuggetDetailBase) -> true
                                else -> currentRoute == item.route
                            }
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    navController.navigate(item.route) {
                                        launchSingleTop = true
                                    }
                                },
                                icon = { Icon(item.icon, contentDescription = item.label) },
                                label = { Text(item.label) }
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
                        startDestination = SubjectNav("coaching_globe")
                    ) {
                        composable<SubjectNav> {
                            val subject = it.toRoute<SubjectNav>()
                            ExploreScreen(
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

                        composable(Routes.MySpace) {
                            MySpaceScreen()
                        }

                        composable(Routes.Profile) {
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
