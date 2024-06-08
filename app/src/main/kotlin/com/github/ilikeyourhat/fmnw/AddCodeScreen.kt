package com.github.ilikeyourhat.fmnw

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class) // Experimental my ass
@Composable
fun AddCodeScreen(
    onDoneClicked: (String) -> Unit
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
                        var text by remember { mutableStateOf(TextFieldValue("")) }
                        TextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            }
                        )
                        Button(
                            onClick = { onDoneClicked(text.text) },
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

@Preview
@Composable
fun AddCodeScreen_default() {
    AddCodeScreen {}
}