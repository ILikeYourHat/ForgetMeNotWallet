package io.github.ilikeyourhat.fmnw.ui.screen.edit.group

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
import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.ui.theme.AppTheme

@Composable
fun EditGroupScreen(
    state: EditGroupScreenState,
    modifier: Modifier = Modifier,
    events: EditGroupEvents = EditGroupEvents.DUMMY
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
                                contentDescription = stringResource(R.string.editGroupScreen_navigationClose)
                            )
                        }
                    },
                    title = {
                        Text(
                            if (state.group.isPersisted()) {
                                stringResource(R.string.editGroupScreen_editGroupTitle)
                            } else {
                                stringResource(R.string.editGroupScreen_addNewGroupTitle)
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
private fun Content(state: EditGroupScreenState, events: EditGroupEvents) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        OutlinedTextField(
            value = state.group.name,
            label = { Text(text = stringResource(R.string.editGroupScreen_inputName)) },
            onValueChange = events::onNameChanged,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = events::onDoneClicked,
            content = {
                Text(stringResource(R.string.editGroupScreen_buttonSave))
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
    EditGroupScreen(EditGroupScreenState())
}

@Preview
@Composable
private fun AddCodeScreen_withBarcode() {
    EditGroupScreen(
        EditGroupScreenState(
            group = Group(
                name = "ABCDEF"
            )
        )
    )
}