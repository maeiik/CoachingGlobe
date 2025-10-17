package ch.coachingglobe

import androidx.compose.material.icons.filled.Place


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ch.coachingglobe.ui.theme.AppTheme

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Explore : BottomNavItem("explore", "Explore", Icons.Filled.Place)
    object MySpace : BottomNavItem("myspace", "My Space", Icons.Filled.CheckCircle)
    object Profile : BottomNavItem("profile", "Profile", Icons.Filled.Person)
}

@Composable
fun App() {
    AppTheme {
        var selectedRoute by remember { mutableStateOf(BottomNavItem.Explore.route) }
        val items = listOf(BottomNavItem.Explore, BottomNavItem.MySpace, BottomNavItem.Profile)

        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = selectedRoute == item.route,
                            onClick = { selectedRoute = item.route },
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
                when (selectedRoute) {
                    BottomNavItem.Explore.route -> ExploreScreen()
                    BottomNavItem.MySpace.route -> MySpaceScreen()
                    BottomNavItem.Profile.route -> ProfileScreen(
                        profile = ProfileDto(
                            firstName = "John",
                            lastName = "Doe",
                            location = "Earth",
                            photoUrl = null
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ScreenLabel(text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}