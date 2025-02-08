package io.github.ilikeyourhat.fmnw.ui.screen.home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.ilikeyourhat.fmnw.R
import io.github.ilikeyourhat.fmnw.model.BarcodeModelType
import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard
import io.github.ilikeyourhat.fmnw.model.Note
import io.github.ilikeyourhat.fmnw.model.WalletItem
import io.github.ilikeyourhat.fmnw.ui.components.CodeFiche
import io.github.ilikeyourhat.fmnw.ui.theme.AppTheme
import de.charlex.compose.BottomAppBarSpeedDialFloatingActionButton
import de.charlex.compose.FloatingActionButtonItem
import de.charlex.compose.SpeedDialFloatingActionButtonState
import de.charlex.compose.SubSpeedDialFloatingActionButtons
import de.charlex.compose.rememberSpeedDialFloatingActionButtonState
import io.github.ilikeyourhat.fmnw.ui.components.CodeFisheActions

@Composable
fun HomeScreen(
    state: HomeScreenState,
    modifier: Modifier = Modifier,
    events: HomeScreenEvents = HomeScreenEvents.DUMMY
) {
    AppTheme {
        val fabState = rememberSpeedDialFloatingActionButtonState()

        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = {
                        if (state.group != null) {
                            Text(state.group.name)
                        } else {
                            Text(stringResource(R.string.app_name))
                        }
                    },
                    navigationIcon = {
                        if (state.group != null) {
                            IconButton(onClick = events::onBackClicked) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.homeScreen_navigationBack)
                                )
                            }
                        }
                    },
                )
            },
            floatingActionButton = {
                AddItemFab(fabState, events)
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
                                Icon(Icons.Filled.Add,
                                    stringResource(R.string.homeScreen_addNewItemButton))
                            }
                        )
                    }
                )
            }
        )
    }
}

@Composable
private fun AddItemFab(
    fabState: SpeedDialFloatingActionButtonState,
    events: HomeScreenEvents
) {
    SubSpeedDialFloatingActionButtons(
        state = fabState,
        items = listOf(
            FloatingActionButtonItem(
                icon = Icons.Default.CreditCard,
                label = stringResource(R.string.homeScreen_addLoyaltyCardButton),
                onFabItemClicked = {
                    events.onAddLoyaltyCardClicked()
                    fabState.stateChange()
                }
            ),
            FloatingActionButtonItem(
                icon = Icons.AutoMirrored.Filled.Note,
                label = stringResource(R.string.homeScreen_addNoteButton),
                onFabItemClicked = {
                    events.onAddNoteClicked()
                    fabState.stateChange()
                }
            ),
            FloatingActionButtonItem(
                icon = Icons.Filled.Apps,
                label = stringResource(R.string.homeScreen_addGroupButton),
                onFabItemClicked = {
                    events.onAddGroupClicked()
                    fabState.stateChange()
                }
            )
        )
    )
}

@Composable
private fun Content(state: HomeScreenState, events: HomeScreenEvents) {
    if (state.items.isEmpty()) {
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
        Text(stringResource(R.string.homeScreen_emptyContentHint))
    }
}

@Composable
private fun NonEmptyContent(state: HomeScreenState, events: HomeScreenEvents) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = state.items,
            key = { it.id!! }
        ) { code ->
            val fisheEvents = object : CodeFisheActions {
                override fun onClick() {
                    events.onItemClicked(code)
                }

                override fun onEditClick() {
                    events.onEditItemClicked(code)
                }

                override fun onDeleteClick() {
                    events.onDeleteItemClicked(code)
                }

            }
            CodeFiche(
                headline = code.name,
                icon = getIconFor(code),
                events = fisheEvents,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillParentMaxWidth()
                    .animateItem(
                        fadeInSpec = null,
                        fadeOutSpec = null,
                        placementSpec = spring(
                            stiffness = Spring.StiffnessMediumLow,
                            visibilityThreshold = IntOffset.VisibilityThreshold
                        )
                    )
            )
        }
    }
}

private fun getIconFor(item: WalletItem): ImageVector {
    return when (item) {
        is LoyaltyCard -> Icons.Default.CreditCard
        is Note -> Icons.AutoMirrored.Filled.Note
        is Group -> Icons.Default.Apps
    }
}

@Preview
@Composable
private fun HomeScreen_empty() {
    HomeScreen(HomeScreenState())
}

@Preview
@Composable
private fun HomeScreen_full() {
    HomeScreen(
        HomeScreenState(
            items = listOf(
                LoyaltyCard(1, "Supercode", null, "test", BarcodeModelType.QR_CODE),
                LoyaltyCard(2, "Supercode2345", null, "test2345", null)
            )
        )
    )
}
