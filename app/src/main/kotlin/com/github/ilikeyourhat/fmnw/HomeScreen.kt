package com.github.ilikeyourhat.fmnw

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
    codes: List<String>,
    onFabClicked: () -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Title") })
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onFabClicked,
                    content = {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                )
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    LazyColumn {
                        items(codes) { code ->
                            Text(code)
                        }
                    }
                }
            }

        )
    }
}

@Preview
@Composable
fun HomeScreen_default() {
    HomeScreen(emptyList()) {  }
}