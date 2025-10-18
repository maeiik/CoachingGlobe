// kotlin
package ch.coachingglobe

import GCImage
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ch.coachingglobe.mySpacesTabs.Empty
import kotlinx.serialization.Serializable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainerScreen(navController: NavController) {
    GCScaffold("Trainer", null) {
        Column(modifier = Modifier.fillMaxSize()) {

            val tabTitles = listOf("Trainer Requests", "Trainer Overview")
            val pagerState = rememberPagerState(0, 0f, { tabTitles.size })

            TabRow(selectedTabIndex = pagerState.currentPage) {
                tabTitles.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { pagerState.requestScrollToPage(index) },
                        text = { Text(tab) }
                    )
                }
            }

            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> TrainerRequestsScreen()
                    1 -> TrainerOverviewScreen()
                }
            }
        }
    }
}

@Composable
fun TrainerRequestsScreen(viewModel: DataViewModel = LocalDataViewModel.current) {
    val items =
        viewModel.myNuggets.collectAsStateWithLifecycle().value.filter { it.value.status == DataViewModel.NuggetStatus.REQUESTED }
            .map {
                Request(
                    1,
                    UserDto(1, "Coach", "Zimmerwald"), it.value.nugget
                )
            }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { request ->
            RequestCard(
                request = request,
                onAccept = { viewModel.onAcceptClicked(request.nugget.id) },
                onDecline = { viewModel.onDeclineClicked(request.nugget.id) })
        }
    }
}

@Composable
fun TrainerOverviewScreen() {
    Empty()
}

@Composable
fun RequestCard(
    request: Request,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    val user = request.user
    val nugget = request.nugget
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GCImage(user)

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Request from ${user.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Course: ${nugget.title}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = onAccept,
                    modifier = Modifier
                        .size(36.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Accept",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                IconButton(
                    onClick = onDecline,
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.Red, shape = CircleShape)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Decline",
                        tint = Color.White
                    )
                }
            }
        }
    }
}