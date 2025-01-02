package net.kasasbeh.islamicunits.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import net.kasasbeh.islamicunits.R

@Composable
fun ConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    text: String,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        dismissButton = {
            OutlinedButton(onClick = onDismissRequest) { Text(stringResource(R.string.dismiss)) }
        },
        confirmButton = {
            Button(onClick = onConfirm) { Text(stringResource(R.string.confirm)) }
        },
        text = { Text(text) },
        title = { Text(title) }
    )
}