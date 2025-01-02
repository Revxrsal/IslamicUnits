package net.kasasbeh.islamicunits.components.favorites

import androidx.compose.runtime.Composable
import net.kasasbeh.islamicunits.components.ConfirmationDialog
import net.kasasbeh.islamicunits.data.room.Favorite

@Composable
fun FavoritesDeleteDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    favorite: Favorite
) {
    ConfirmationDialog(
        onDismissRequest = onDismissRequest,
        onConfirm = onConfirm,
        title = "Are you sure?",
        text = "Are you sure you would like to delete '${favorite.name}'?"
    )
}