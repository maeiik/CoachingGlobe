package ch.coachingglobe.mySpacesTabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MySpaceFavoritesTab(
    modifier: Modifier = Modifier,
    onOpen: (String) -> Unit = {}
) {
    // simple favorites example: first two items as favorites
//    val favorites = sampleCoachableSets.take(2)
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
//        items(favorites, key = { it.id }) { set ->
//            CoachableSetCard(
//                set = set,
//                onClick = { onOpen(set.id) }
//            )
//        }
        item { Empty() }
    }
}
