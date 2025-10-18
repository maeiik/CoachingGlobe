package ch.coachingglobe

import GCImage
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuggetScreen(
    nugget: Nugget,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    viewModel: DataViewModel = LocalDataViewModel.current
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = nugget.title, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Video container (16:9)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                val videoId = extractYoutubeId(nugget.youtubeUrl)
                if (videoId != null) {
                    YouTubePlayer(
                        videoId = videoId,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Invalid video URL",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = nugget.description,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Author card
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val author = nugget.author
                    GCImage(
                        author.user,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = author.user.name,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Profession: ${author.profession}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = author.descr, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            val status = viewModel.myNuggets.collectAsStateWithLifecycle().value[nugget.id]?.status
            when (status) {
                null, DataViewModel.NuggetStatus.NONE -> {
                    Button(
                        onClick = { viewModel.onCouchMeClicked(nugget.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "couch me")
                    }
                }

                DataViewModel.NuggetStatus.REQUESTED -> {
                    Button(
                        onClick = { viewModel.revertRequest(nugget.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Revert Request")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedButton(
                        onClick = { viewModel.onAcceptClicked(nugget.id) },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Accept Request (only for demo)")
                    }
                }

                DataViewModel.NuggetStatus.COACHING -> {
                    Text(
                        text = "You are being coached this nugget.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                DataViewModel.NuggetStatus.FINISHED -> {
                    Text(
                        text = "You have finished this nugget.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun Kuerzel(user: UserDto) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initialsOf(user),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun Kuerzel(name: String) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (name.length > 1) name.substring(0, 2).uppercase() else name,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

fun extractYoutubeId(url: String?): String? {
    val a = url?.split("youtube.com/watch?v=")?.getOrNull(1)
    return a
}

private fun initialsOf(user: UserDto): String {
    val f = user.firstName.firstOrNull()?.uppercaseChar() ?: return "?"
    val l = user.lastName.firstOrNull()?.uppercaseChar() ?: return "$f"
    return "$f$l"
}
