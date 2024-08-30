package com.github.ilikeyourhat.fmnw.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ilikeyourhat.fmnw.ui.theme.AppTheme
import com.github.ilikeyourhat.fmnw.ui.theme.primaryLight

@Composable
fun CodeFiche(
    headline: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        border = BorderStroke(2.dp, primaryLight),
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.CreditCard,
                contentDescription = "",
                modifier = Modifier.minimumInteractiveComponentSize()
            )
            Text(
                text = headline,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            var expanded by remember { mutableStateOf(false) }
            IconButton(
                onClick = { expanded = true }
            ) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "")
                        },
                        text = { Text("Edit") },
                        onClick = {
                            onEditClick()
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
                        },
                        text = { Text("Delete") },
                        onClick = {
                            onDeleteClick()
                            expanded = false
                        }
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun CodeFiche_default() {
    AppTheme {
        CodeFiche(
            headline = "Headline"
        )
    }
}

@Preview
@Composable
fun CodeFiche_long() {
    AppTheme {
        CodeFiche(
            headline = "Headline",
            modifier = Modifier.width(220.dp)
        )
    }
}