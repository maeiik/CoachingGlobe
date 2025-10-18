package ch.coachingglobe.mySpacesTabs


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ch.coachingglobe.DataViewModel
import ch.coachingglobe.LocalDataViewModel
import ch.coachingglobe.NuggetCard
import ch.coachingglobe.NuggetNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySpaceCoachingsTab(
    navController: NavController,
    viewModel: DataViewModel = LocalDataViewModel.current
) {
    val nuggets =
        viewModel.myNuggets.collectAsStateWithLifecycle().value.values.filter { it.status == DataViewModel.NuggetStatus.COACHING }
    if (nuggets.isEmpty()) {
        Empty("No coached nuggets yet")
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
        ) {
            items(nuggets) { nugget ->
                NuggetCard(nugget.nugget) {
                    navController.navigate(NuggetNav(nugget.nugget.id))
                }
            }
        }
    }
}

@Composable
fun Empty(text: String = "Empty") {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}