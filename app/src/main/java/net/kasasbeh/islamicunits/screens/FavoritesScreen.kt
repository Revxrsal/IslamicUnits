package net.kasasbeh.islamicunits.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import net.kasasbeh.islamicunits.FavoritesViewModel
import net.kasasbeh.islamicunits.R
import net.kasasbeh.islamicunits.components.favorites.FavoritesDeleteDialog
import net.kasasbeh.islamicunits.data.room.Favorite
import org.koin.androidx.compose.koinViewModel

object FavoritesScreen : Screen, WithTopAppBar {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val favoritesViewModel = koinViewModel<FavoritesViewModel>()
        val favorites by favoritesViewModel.favorites.collectAsState()
        if (favorites.isEmpty()) {
            NoFavorites()
        } else {
            LazyColumn(modifier = Modifier.padding(10.dp)) {
                items(favorites) {
                    val title = it.firstUnit.unitType.title()
                    ListFavorite(
                        favorite = it,
                        onDelete = { favoritesViewModel.removeFavorite(it) },
                        onClick = {
                            navigator.push(
                                ConverterScreen(
                                    title = title,
                                    units = it.firstUnit.unitType.getUnits(),
                                    favorite = it
                                )
                            )
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun NoFavorites() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.no_favorites),
                fontWeight = Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                lineHeight = 37.5.sp
            )
            Image(
                painter = painterResource(R.drawable.nothing),
                contentDescription = "No favorites",
                modifier = Modifier.size(200.dp)
            )
            Text(stringResource(R.string.add_from_converters))
        }
    }

    @Composable
    private fun ListFavorite(
        favorite: Favorite,
        onDelete: () -> Unit = {},
        onClick: () -> Unit = {},
    ) {
        var showDelete by remember { mutableStateOf(false) }
        if (showDelete)
            FavoritesDeleteDialog(
                onDismissRequest = { showDelete = false },
                onConfirm = onDelete,
                favorite = favorite
            )
        Card(
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 3.dp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 10.dp),
            onClick = onClick,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = favorite.name,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 20.sp
                    )
                    Text(
                        text = "${favorite.firstValue} ${favorite.firstUnit.localizedName()} = ${favorite.secondValue} ${favorite.secondUnit.localizedName()}",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.5.dp),
                    )
                }
                IconButton(onClick = { showDelete = true }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete favorite"
                    )
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun ScreenTopAppBar() {
        val navigator = LocalNavigator.currentOrThrow
        TopAppBar(
            title = { Text(stringResource(R.string.favorites)) },
            navigationIcon = {
                IconButton(onClick = { navigator.push(HomeScreen) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }

}