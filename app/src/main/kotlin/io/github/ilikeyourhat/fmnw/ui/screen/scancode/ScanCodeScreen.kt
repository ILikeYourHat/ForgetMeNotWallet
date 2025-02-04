package io.github.ilikeyourhat.fmnw.ui.screen.scancode

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.ilikeyourhat.fmnw.R
import io.github.ilikeyourhat.fmnw.ui.components.CameraPreview
import io.github.ilikeyourhat.fmnw.ui.theme.AppTheme

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
                                contentDescription = stringResource(R.string.scanCodeScreen_navigationClose)
                            )
                        }
                    },
                    title = { Text(stringResource(R.string.scanCodeScreen_scanCodeTitle)) }
                )
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    if (state.permissionGranted && !state.barcodeDetected) {
                        CameraPreview(events::onBarcodeDetected)
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = events::onInputManuallyClicked,
                    icon = { Icon(Icons.Filled.Edit, null) },
                    text = { Text(text = stringResource(R.string.scanCodeScreen_inputManuallyButton)) },
                )
            }
        )
    }
}