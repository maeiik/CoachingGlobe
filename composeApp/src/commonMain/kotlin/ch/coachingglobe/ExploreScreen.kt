package ch.coachingglobe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(onClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Explore") },
            )
        }
    ) { paddingValues ->
        // Content goes here
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            Button(onClick = onClick) {
                Text("Go to Subjects")
            }
        }
    }
}