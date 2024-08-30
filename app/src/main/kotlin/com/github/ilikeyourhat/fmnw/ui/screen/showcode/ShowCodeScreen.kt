package com.github.ilikeyourhat.fmnw.ui.screen.showcode

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType
import com.github.ilikeyourhat.fmnw.model.LoyaltyCard
import com.github.ilikeyourhat.fmnw.model.Note
import com.github.ilikeyourhat.fmnw.ui.components.BarcodePreview
import com.github.ilikeyourhat.fmnw.ui.theme.AppTheme

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
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    title = { Text(state.item.name) }
                )
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    if (state.item is LoyaltyCard) {
                        BarcodePreview(
                            barcodeContainer = state.item,
                            modifier = Modifier.fillMaxWidth()
                                .padding(16.dp)
                        )
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
            item = LoyaltyCard(
                id = 123,
                name = "My saved code",
                barcodeType = BarcodeModelType.QR_CODE,
                value = "123456"
            )
        )
    )
}

@Preview
@Composable
fun ShowCodeScreen_withTextCode() {
    ShowCodeScreen(
        ShowCodeScreenState(
            item = Note(
                id = 123,
                name = "My saved code",
                value = "123456"
            )
        )
    )
}