package com.github.ilikeyourhat.fmnw.ui.addcode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class) // Experimental my ass
@Composable
fun AddCodeScreen(
    state: AddCodeScreenState,
    events: AddCodeEvents = AddCodeEvents.DUMMY
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Title") })
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    Column {
                        Text(
                            text = "Hello World!",
                            fontSize = 16.sp,
                        )
                        OutLineTextField(state.code, events)
                        Button(
                            onClick = events::onDoneClicked,
                            content = {
                                Text("OK")
                            }
                        )
                    }
                }
            }

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