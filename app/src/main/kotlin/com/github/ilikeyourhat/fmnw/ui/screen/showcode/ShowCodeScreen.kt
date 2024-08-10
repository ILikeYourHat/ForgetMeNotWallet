package com.github.ilikeyourhat.fmnw.ui.screen.showcode

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.ilikeyourhat.fmnw.model.CodeModel
import com.github.ilikeyourhat.fmnw.ui.core.theme.AppTheme
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType

@OptIn(ExperimentalMaterial3Api::class) // Experimental my ass
@Composable
fun ShowCodeScreen(
    state: ShowCodeScreenState,
    events: ShowCodeScreenEvents = ShowCodeScreenEvents.DUMMY
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
                    title = { Text(state.code.name) }
                )
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    if (state.code.type == "raw_text") {
                        Text(state.code.value)
                    } else {
                        Barcode(type = BarcodeType.QR_CODE, value = state.code.value)
                    }
                }
            }

        )
    }
}

@Preview
@Composable
fun ShowCodeScreen_withBarcode() {
    ShowCodeScreen(
        ShowCodeScreenState(
            code = CodeModel(
                id = 123,
                name = "My saved code",
                type = "qr_code",
                value = "123456"
            )
        )
    )
}