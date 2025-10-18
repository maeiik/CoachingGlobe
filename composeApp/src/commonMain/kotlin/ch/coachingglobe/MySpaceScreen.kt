package ch.coachingglobe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ch.coachingglobe.mySpacesTabs.MySpaceCoachingsTab
import ch.coachingglobe.mySpacesTabs.MySpaceFavoritesTab
import ch.coachingglobe.mySpacesTabs.MySpaceRequestsTab

private data class MySpaceTab(val route: String, val title: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySpaceScreen(navController: NavController) {
    val nestedNav = rememberNavController()
    val tabs = listOf(
        MySpaceTab("myspace/coachings", "Coachings"),
        MySpaceTab("myspace/favorites", "Favorites"),
        MySpaceTab("myspace/requests", "Requests")
    )

    val backStackEntry by nestedNav.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val selectedIndex = tabs.indexOfFirst { currentRoute?.startsWith(it.route) == true }
        .let { if (it == -1) 0 else it }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Space") },
            )
        },
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TabRow(selectedTabIndex = selectedIndex) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedIndex == index,
                        onClick = {
                            nestedNav.navigate(tab.route) {
                                launchSingleTop = true
                            }
                        },
                        text = { Text(tab.title) }
                    )
                }
            }

            NavHost(
                navController = nestedNav,
                startDestination = tabs.first().route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(tabs[0].route) {
                    MySpaceCoachingsTab(navController)
                }
                composable(tabs[1].route) {
                    MySpaceFavoritesTab(
                        onOpen = { setId -> nestedNav.navigate("couchable/$setId") }
                    )
                }
                composable(tabs[2].route) {
                    MySpaceRequestsTab(navController)
                }
            }
        }
    }
}
