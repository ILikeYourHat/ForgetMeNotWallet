package io.github.ilikeyourhat.fmnw.ui.screen.edit.loyaltycard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ilikeyourhat.fmnw.model.BarcodeModelType
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard
import io.github.ilikeyourhat.fmnw.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class) // Experimental my ass
@Composable
fun EditLoyaltyCardScreen(
    state: EditLoyaltyCardScreenState,
    events: EditLoyaltyCardEvents = EditLoyaltyCardEvents.DUMMY
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
                    title = {
                        val titleText = if (state.loyaltyCard.isPersisted()) {
                            "Edit code"
                        } else {
                            "Add new code"
                        }
                        Text(titleText)
                    }
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
private fun Content(state: EditLoyaltyCardScreenState, events: EditLoyaltyCardEvents) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        OutlinedTextField(
            value = state.loyaltyCard.name,
            label = { Text(text = "Name") },
            onValueChange = events::onNameChanged,
            modifier = Modifier.fillMaxWidth()
        )
        FormatPicker(
            selectedFormat = state.loyaltyCard.barcodeType,
            events = events,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = state.loyaltyCard.value,
            label = { Text(text = "Value") },
            onValueChange = events::onValueChanged,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = events::onDoneClicked,
            content = {
                Text("Save")
            },
            modifier = Modifier.padding(top = 8.dp)
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormatPicker(
    selectedFormat: BarcodeModelType?,
    events: EditLoyaltyCardEvents,
    modifier: Modifier = Modifier
) {
    val options = listOf("Raw text") + BarcodeModelType.entries.map { it.toString() }
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(),
            value = selectedFormat?.toString() ?: "Raw text",
            onValueChange = { },
            readOnly = true,
            label = { Text("Barcode type") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        val format = BarcodeModelType.entries.singleOrNull { option == it.toString() }
                        events.onFormatChanged(format)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Preview
@Composable
fun AddCodeScreen_default() {
    EditLoyaltyCardScreen(EditLoyaltyCardScreenState())
}

@Preview
@Composable
fun AddCodeScreen_withBarcode() {
    EditLoyaltyCardScreen(
        EditLoyaltyCardScreenState(
            loyaltyCard = LoyaltyCard(
                barcodeType = BarcodeModelType.QR_CODE,
                value = "ABCDEF"
            )
        )
    )
}