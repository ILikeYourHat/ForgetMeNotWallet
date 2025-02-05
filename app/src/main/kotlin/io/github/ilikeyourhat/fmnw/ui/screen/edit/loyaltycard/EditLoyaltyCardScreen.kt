package io.github.ilikeyourhat.fmnw.ui.screen.edit.loyaltycard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ilikeyourhat.fmnw.R
import io.github.ilikeyourhat.fmnw.model.BarcodeModelType
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard
import io.github.ilikeyourhat.fmnw.ui.theme.AppTheme

@Composable
fun EditLoyaltyCardScreen(
    state: EditLoyaltyCardScreenState,
    modifier: Modifier = Modifier,
    events: EditLoyaltyCardEvents = EditLoyaltyCardEvents.DUMMY
) {
    AppTheme {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = events::onCloseClicked) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(R.string.editLoyaltyCardScreen_navigationClose)
                            )
                        }
                    },
                    title = {
                        Text(
                            if (state.loyaltyCard.isPersisted()) {
                                stringResource(R.string.editLoyaltyCardScreen_editCodeTitle)
                            } else {
                                stringResource(R.string.editLoyaltyCardScreen_addNewCodeTitle)
                            }
                        )
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
            label = { Text(text = stringResource(R.string.editLoyaltyCardScreen_inputName)) },
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
            label = { Text(text = stringResource(R.string.editLoyaltyCardScreen_inputValue)) },
            onValueChange = events::onValueChanged,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = events::onDoneClicked,
            content = {
                Text(stringResource(R.string.editLoyaltyCardScreen_buttonSave))
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun FormatPicker(
    selectedFormat: BarcodeModelType?,
    events: EditLoyaltyCardEvents,
    modifier: Modifier = Modifier
) {
    val possibilities = mapOf(null to stringResource(R.string.barcodeType_rawText)) +
            BarcodeModelType.entries.associateWith { stringResource(it.nameStringRes) }
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
            value = possibilities.getValue(selectedFormat),
            onValueChange = { },
            readOnly = true,
            label = { Text(stringResource(R.string.editLoyaltyCardScreen_inputBarcodeType)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            possibilities.forEach { (format, label) ->
                DropdownMenuItem(
                    text = { Text(label) },
                    onClick = {
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
private fun AddCodeScreen_default() {
    EditLoyaltyCardScreen(EditLoyaltyCardScreenState())
}

@Preview
@Composable
private fun AddCodeScreen_withBarcode() {
    EditLoyaltyCardScreen(
        EditLoyaltyCardScreenState(
            loyaltyCard = LoyaltyCard(
                barcodeType = BarcodeModelType.QR_CODE,
                value = "ABCDEF"
            )
        )
    )
}