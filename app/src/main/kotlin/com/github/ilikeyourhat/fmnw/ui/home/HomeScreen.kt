package com.github.ilikeyourhat.fmnw.ui.home

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class) // Experimental my ass
@Composable
fun HomeScreen(
    state: HomeScreenState,
    events: HomeScreenEvents = HomeScreenEvents.DUMMY
) {
    Log.e("Compose", "Recomposition: $state")
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Title") })
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = events::onAddCodeClicked,
                    content = {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                )
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    Content(state)
                }
            }

        )
    }
}

@Composable
private fun Content(state: HomeScreenState) {
    Log.e("Compose", "Content")
        LazyColumn {
            items(state.codes) { code ->
                Text(code)
            }
        }
}

@Preview
@Composable
fun HomeScreen_default() {
    HomeScreen(HomeScreenState())
}