package net.kasasbeh.islamicunits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import net.kasasbeh.islamicunits.data.room.Favorite
import net.kasasbeh.islamicunits.data.room.FavoritesDatabase

class FavoritesViewModel(
    private val database: FavoritesDatabase
) : ViewModel() {

    private val _favorites = MutableStateFlow(emptyList<Favorite>())
    val favorites = _favorites.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            database.dao().getFavorites()
                .flowOn(Dispatchers.IO)
                .conflate()
                .distinctUntilChanged()
                .collect {
                    _favorites.value = it
                }
        }
    }

    fun addFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        database.dao().addFavorite(favorite)
    }

    fun removeFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        database.dao().deleteFavorite(favorite)
    }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        database.dao().updateFavorite(favorite)
    }

    fun clearFavorites() = viewModelScope.launch(Dispatchers.IO) {
        database.dao().clearFavorites()
    }
}