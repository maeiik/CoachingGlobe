package ch.coachingglobe.mySpacesTabs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ch.coachingglobe.DataViewModel
import ch.coachingglobe.LocalDataViewModel
import ch.coachingglobe.NuggetCard
import ch.coachingglobe.NuggetNav

@Composable
fun MySpaceRequestsTab(
    navController: NavController,
    viewModel: DataViewModel = LocalDataViewModel.current
) {
    val requests =
        viewModel.myNuggets.collectAsStateWithLifecycle().value.values.filter { it.status == DataViewModel.NuggetStatus.REQUESTED }
    if (requests.isNotEmpty()) {
        LazyColumn {
            items(requests) { NuggetCard(it.nugget) { navController.navigate(NuggetNav(it.id)) } }
        }
    } else {
        Empty()
    }
}
