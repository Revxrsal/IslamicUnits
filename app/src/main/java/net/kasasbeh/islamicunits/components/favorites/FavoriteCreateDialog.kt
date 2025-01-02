package net.kasasbeh.islamicunits.components.favorites

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun FavoriteDialog(
    onSubmit: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var label by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        dismissButton = {
            OutlinedButton(onClick = onDismissRequest) { Text("Dismiss") }
        },
        confirmButton = {
            Button(
                onClick = {
                    onDismissRequest()
                    onSubmit(label)
                },
                enabled = label.isNotEmpty(),
                content = { Text("Add") }
            )
        },
        text = {
            OutlinedTextField(
                value = label,
                onValueChange = { label = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Favorite name") }
            )
        },
        title = { Text("Add a new favorite") }
    )
}