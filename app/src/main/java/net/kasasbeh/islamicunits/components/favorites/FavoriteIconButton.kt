package net.kasasbeh.islamicunits.components.favorites

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun FavoriteIconButton(
    onClick: () -> Unit,
    isFavorite: Boolean,
    enabled: Boolean = true
) {
    IconButton(onClick = onClick, enabled = enabled) {
        Icon(
            imageVector = if (isFavorite)
                Icons.Filled.Favorite
            else
                Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite"
        )
    }
}