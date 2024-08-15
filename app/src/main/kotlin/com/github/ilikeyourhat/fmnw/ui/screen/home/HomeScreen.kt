package com.github.ilikeyourhat.fmnw.ui.screen.home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.github.ilikeyourhat.fmnw.R
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType
import com.github.ilikeyourhat.fmnw.model.CodeModel
import com.github.ilikeyourhat.fmnw.ui.components.CodeFiche
import com.github.ilikeyourhat.fmnw.ui.core.theme.AppTheme
import de.charlex.compose.BottomAppBarSpeedDialFloatingActionButton
import de.charlex.compose.FloatingActionButtonItem
import de.charlex.compose.SubSpeedDialFloatingActionButtons
import de.charlex.compose.rememberSpeedDialFloatingActionButtonState

@OptIn(ExperimentalMaterial3Api::class) // Experimental my ass
@Composable
fun HomeScreen(
    state: HomeScreenState,
    events: HomeScreenEvents = HomeScreenEvents.DUMMY
) {
    AppTheme {

        val fabState = rememberSpeedDialFloatingActionButtonState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) }
                )
            },
            floatingActionButton = {
                SubSpeedDialFloatingActionButtons(
                    state = fabState,
                    items = listOf(
                        FloatingActionButtonItem(
                            icon = Icons.Default.Abc,
                            label = "From text",
                            onFabItemClicked = {
                                events.onAddTextCodeClicked()
                                fabState.stateChange()
                            }
                        ),
                        FloatingActionButtonItem(
                            icon = Icons.Filled.CameraAlt,
                            label = "From photo",
                            onFabItemClicked = {
                                events.onScanBarcodeFromCameraClicked()
                                fabState.stateChange()
                            }
                        ),
                        FloatingActionButtonItem(
                            icon = Icons.Filled.Image,
                            label = "From image",
                            onFabItemClicked = {
                                events.onScanBarcodeFromImageClicked()
                                fabState.stateChange()
                            }
                        )
                    )
                )
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    Content(state, events)
                }
            },
            bottomBar = {
                BottomAppBar(
                    actions = {},
                    floatingActionButton = {
                        BottomAppBarSpeedDialFloatingActionButton(
                            state = fabState,
                            content = {
                                Icon(Icons.Filled.Add, "Floating action button.")
                            }
                        )
                    }
                )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NonEmptyContent(state: HomeScreenState, events: HomeScreenEvents) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = state.codes,
            key = { it.id!! }
        ) { code ->
            CodeFiche(
                headline = code.name,
                onClick = { events.onShowCodeClicked(code) },
                onEditClick = { events.onEditCodeClicked(code) },
                onDeleteClick = { events.onDeleteCodeClicked(code) },
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillParentMaxWidth()
                    .animateItemPlacement(
                        animationSpec = spring(
                            stiffness = Spring.StiffnessMediumLow,
                            visibilityThreshold = IntOffset.VisibilityThreshold
                        )
                    )
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
                CodeModel(1, "Supercode", "test", BarcodeModelType.QR_CODE),
                CodeModel(2, "Supercode2345", "test2345", null)
            )
        )
    )
}