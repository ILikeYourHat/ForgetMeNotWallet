package com.github.ilikeyourhat.fmnw.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ilikeyourhat.fmnw.R
import com.github.ilikeyourhat.fmnw.ui.components.CodeFiche
import com.github.ilikeyourhat.fmnw.ui.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class) // Experimental my ass
@Composable
fun HomeScreen(
    state: HomeScreenState,
    events: HomeScreenEvents = HomeScreenEvents.DUMMY
) {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) }
                )
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
                    Content(state, events)
                }
            }
        )
    }
}

@Composable
private fun Content(state: HomeScreenState, events: HomeScreenEvents) {
    if (state.codes.isEmpty()) {
        EmptyContent()
    } else {
        NonEmptyContent(state, events)
    }
}

@Composable
private fun EmptyContent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Add a new code by clicking the + button")
    }
}

@Composable
private fun NonEmptyContent(state: HomeScreenState, events: HomeScreenEvents) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.codes) { code ->
            CodeFiche(
                headline = code.name,
                text = code.value,
                onDeleteClicked = { events.onDeleteCodeClicked(code) },
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillParentMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun HomeScreen_empty() {
    HomeScreen(HomeScreenState())
}

@Preview
@Composable
fun HomeScreen_full() {
    HomeScreen(
        HomeScreenState(
            codes = listOf(
                CodeState(1, "Supercode", "test"),
                CodeState(2, "Supercode2345", "test2345")
            )
        )
    )
}