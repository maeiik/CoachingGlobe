package ch.coachingglobe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreenWithSubjectsWithLoading(
    title: String,
    data: StateX<DataDto>?,
    onSubjectClicked: (Subject) -> Unit,
    onBack: (() -> Unit)?,
) {
    GCScaffold(title, onBack) {
        when (data) {
            is StateX.Error<DataDto> -> {
                Error(data)
            }

            is StateX.Loading<*> -> {
                CircularProgressIndicator()
            }

            is StateX.Success<DataDto> -> {
                SubjectList(
                    subjects = data.data.subjects.map { it.toSubject(data.data) },
                    onSubjectClicked = onSubjectClicked
                )
            }

            null -> {}
        }
    }
}

@Composable
private fun Error(data: StateX.Error<DataDto>) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: ${data.error}",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectListScreen(
    title: String,
    subjects: List<Subject>,
    onSubjectClicked: (Subject) -> Unit,
    onBack: (() -> Unit)?,
) {
    GCScaffold(title, onBack) {
        SubjectList(subjects, onSubjectClicked)
    }
}

@Composable
private fun SubjectList(
    subjects: List<Subject>,
    onSubjectClicked: (Subject) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = subjects) {
            SubjectItem(it, onClick = { onSubjectClicked(it) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoachableSetListScreen(
    title: String,
    coachableSetDto: List<CoachableSet>,
    onSubjectClicked: (CoachableSet) -> Unit,
    onBack: (() -> Unit)?,
) {
    GCScaffold(title, onBack) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = coachableSetDto) {
                CoachableSetItem(it, onClick = { onSubjectClicked(it) })
            }
        }
    }
}

@Composable
fun CoachableSetItem(set: CoachableSet, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = set.title, style = MaterialTheme.typography.titleMedium)
            set.description?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Nuggets: ${set.nuggets.size}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun SubjectItem(subject: Subject, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = subject.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = subject.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Subjects: ${subject.subjects?.size ?: 0}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Coachable Sets: ${subject.coachableSets?.size ?: 0}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
