package io.github.ilikeyourhat.fmnw.ui.screen.edit.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ilikeyourhat.fmnw.R
import io.github.ilikeyourhat.fmnw.model.Note
import io.github.ilikeyourhat.fmnw.ui.theme.AppTheme

@Composable
fun EditNoteScreen(
    state: EditNoteScreenState,
    modifier: Modifier = Modifier,
    events: EditNoteEvents = EditNoteEvents.DUMMY
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
                                contentDescription = stringResource(R.string.editNoteScreen_navigationClose)
                            )
                        }
                    },
                    title = {
                        Text(
                            if (state.note.isPersisted()) {
                                stringResource(R.string.editNoteScreen_editNoteTitle)
                            } else {
                                stringResource(R.string.editNoteScreen_addNewNoteTitle)
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
private fun Content(state: EditNoteScreenState, events: EditNoteEvents) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        OutlinedTextField(
            value = state.note.name,
            label = { Text(text = stringResource(R.string.editNoteScreen_inputName)) },
            onValueChange = events::onNameChanged,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = state.note.value,
            label = { Text(text = stringResource(R.string.editNoteScreen_inputValue)) },
            onValueChange = events::onValueChanged,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = events::onDoneClicked,
            content = {
                Text(stringResource(R.string.editNoteScreen_buttonSave))
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun AddCodeScreen_default() {
    EditNoteScreen(EditNoteScreenState())
}

@Preview
@Composable
private fun AddCodeScreen_withBarcode() {
    EditNoteScreen(
        EditNoteScreenState(
            note = Note(
                name = "Test",
                value = "ABCDEF"
            )
        )
    )
}
