package ch.coachingglobe

import GCImage
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuggetScreen(
    nugget: NuggetDto,
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


            Spacer(modifier = Modifier.height(16.dp))

            nugget.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Author card
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val author = nugget.author
                    if (!author?.photoUrl.isNullOrBlank()) {
                        GCImage(
                            author.photoUrl,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = initialsOf(author),
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${author?.firstName} ${author?.lastName}",
                            fontWeight = FontWeight.SemiBold
                        )
                        author?.title?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        author?.bio?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = it, style = MaterialTheme.typography.bodySmall)
                        }
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

fun extractYoutubeId(url: String?): String? {
    val a = url?.split("youtube.com/watch?v=")?.getOrNull(1)
    return a
}

private fun initialsOf(author: AuthorDto?): String {
    val f = author?.firstName?.firstOrNull()?.uppercaseChar() ?: return "?"
    val l = author.lastName.firstOrNull()?.uppercaseChar() ?: return "$f"
    return "$f$l"
}
