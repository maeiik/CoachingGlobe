// kotlin
package ch.coachingglobe

import GCImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val name: String,
    val email: String,
)

@Serializable
data class RequestDto(
    val id: String,
    val user: UserDto,
    val coachableSet: CoachableSetDto,
    val status: String // E.g., "pending", "accepted", "rejected"
)

@Serializable
data class RequestsDto(
    val requests: List<RequestDto>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainerScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trainer") },
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {

            // TabView
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
                    0 -> TrainerRequestsScreen() // Trainer Requests tab
                    1 -> TrainerOverviewScreen() // Trainer Overview tab
                }
            }
        }
    }
}

@Composable
fun TrainerRequestsScreen() {
    // Example of mock data
    val requests = listOf(
        RequestDto(
            id = "1",
            user = UserDto(id = "1", name = "John Doe", email = "john.doe@example.com"),
            coachableSet = CoachableSetDto(
                id = "1",
                title = "Leadership Coaching",
                description = "A deep dive into leadership principles",
                nuggets = listOf(
                    NuggetDto(
                        id = "1",
                        title = "Goal Setting",
                        description = "Set achievable leadership goals."
                    ),
                    NuggetDto(
                        id = "2",
                        title = "Effective Communication",
                        description = "Learn how to communicate with impact."
                    )
                )
            ),
            status = "pending"
        ),
        RequestDto(
            id = "2",
            user = UserDto(id = "2", name = "Jane Smith", email = "jane.smith@example.com"),
            coachableSet = CoachableSetDto(
                id = "2",
                title = "Stress Management",
                description = "Techniques to manage stress in the workplace",
                nuggets = listOf(
                    NuggetDto(
                        id = "3",
                        title = "Breathing Exercises",
                        description = "Practice deep breathing for stress relief."
                    ),
                    NuggetDto(
                        id = "4",
                        title = "Mindfulness",
                        description = "Use mindfulness to stay calm in stressful situations."
                    )
                )
            ),
            status = "accepted"
        )
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(requests) { request ->
            RequestCard(request, {}, {})
        }
    }
}

@Composable
fun RequestItem(request: RequestDto) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Request from ${request.user.name}")
        Text(
            text = "Coachable Set: ${request.coachableSet.title}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(text = "Status: ${request.status}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun TrainerOverviewScreen() {
    // For simplicity, we'll use mock data
    val completedSessions = 10
    val upcomingSessions = 5

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Trainer Overview", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Completed Sessions: $completedSessions",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Upcoming Sessions: $upcomingSessions",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun RequestCard(
    request: RequestDto,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    val user = request.user
    val coachableSet = request.coachableSet
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
            // User Image
            GCImage("https://via.placeholder.com/150")

            Spacer(modifier = Modifier.width(16.dp))

            // User Info and Course Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Request from ${user.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Which course: ${coachableSet.title}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Accept and Decline Buttons
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


@Composable
fun CoachableSetCard(item: CoachableSetDto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 88.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            // Thumbnail placeholder; replace with real image loader if available
            Surface(
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.medium),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
            ) {
//                if (item.thumbnailUrl != null) {
//                    // If you have an image loader (Coil, etc.), load here
//                    // For now placeholder
//                    Box(contentAlignment = Alignment.Center) {
//                        Text("Img", style = MaterialTheme.typography.bodySmall)
//                    }
//                } else {
//                    Box(contentAlignment = Alignment.Center) {
//                        Text(item.title.take(1).uppercase(), style = MaterialTheme.typography.bodySmall)
//                    }
//                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (!item.description.isNullOrBlank()) {
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
