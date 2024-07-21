package com.github.ilikeyourhat.fmnw.ui.screen.addcode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ilikeyourhat.fmnw.ui.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class) // Experimental my ass
@Composable
fun AddCodeScreen(
    state: AddCodeScreenState,
    events: AddCodeEvents = AddCodeEvents.DUMMY
) {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = events::onCloseClicked) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    title = { Text("Add new code") }
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
private fun Content(state: AddCodeScreenState, events: AddCodeEvents) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        OutLineTextField(state.code, events)
        Button(
            onClick = events::onDoneClicked,
            content = {
                Text("Add")
            },
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun OutLineTextField(code: String, events: AddCodeEvents) {
    OutlinedTextField(
        value = code,
        label = { Text(text = "Enter your code") },
        onValueChange = events::onCodeChanged
    )
}

@Preview
@Composable
fun AddCodeScreen_default() {
    AddCodeScreen(AddCodeScreenState())
}