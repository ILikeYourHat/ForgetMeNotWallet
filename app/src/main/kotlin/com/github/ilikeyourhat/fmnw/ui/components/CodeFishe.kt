package com.github.ilikeyourhat.fmnw.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ilikeyourhat.fmnw.ui.core.theme.AppTheme
import com.github.ilikeyourhat.fmnw.ui.core.theme.primaryLight

@Composable
fun CodeFiche(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        border = BorderStroke(2.dp, primaryLight),
        modifier = modifier
    ) {
        Row {
            Column {
                Text(
                    text = "Headline",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 8.dp
                        )
                )
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 8.dp
                        )
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.run { align(alignment = Alignment.End) }
            ) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
            }
        }

    }
}

@Composable
private fun CodeFicheDropDownMenu(
    initialExpanded: Boolean = false
) {
    var expanded by remember { mutableStateOf(initialExpanded) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            trailingIcon = {
                Icons.Filled.Delete
            },
            text = {  Text("Delete") },
            onClick = {  }
        )
    }
}

@Preview
@Composable
fun CodeFiche_default() {
    AppTheme {
        CodeFiche(
            text = "My secret code"
        )
    }
}

@Preview
@Composable
fun CodeFiche_long() {
    AppTheme {
        CodeFiche(
            text = "My secret code",
            modifier = Modifier.width(200.dp)
        )
    }
}

@Preview
@Composable
fun CodeFicheDropDownMenu_expanded() {
    AppTheme {
        Box {
            CodeFicheDropDownMenu(
                initialExpanded = true
            )
        }
    }
}