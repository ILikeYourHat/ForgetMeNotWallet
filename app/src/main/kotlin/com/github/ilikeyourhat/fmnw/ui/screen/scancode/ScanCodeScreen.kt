package com.github.ilikeyourhat.fmnw.ui.screen.scancode

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.ilikeyourhat.fmnw.ui.components.CameraPreview
import com.github.ilikeyourhat.fmnw.ui.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class) // Experimental my ass
@Composable
fun ScanCodeScreen(
    state: ScanCodeScreenState,
    events: ScanCodeScreenEvents
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
                    title = { Text("Scan code") }
                )
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    if (state.permissionGranted && !state.barcodeDetected) {
                        CameraPreview(events::onBarcodeDetected)
                    } else {
                        CircularProgressIndicator()
                    }
                }
            }

        )
    }
}