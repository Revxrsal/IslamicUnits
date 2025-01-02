package net.kasasbeh.islamicunits.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import net.kasasbeh.islamicunits.FavoritesViewModel
import net.kasasbeh.islamicunits.components.CopyButton
import net.kasasbeh.islamicunits.components.favorites.FavoriteDialog
import net.kasasbeh.islamicunits.components.favorites.FavoriteIconButton
import net.kasasbeh.islamicunits.components.favorites.FavoritesDeleteDialog
import net.kasasbeh.islamicunits.data.School
import net.kasasbeh.islamicunits.data.room.Favorite
import net.kasasbeh.islamicunits.repository.DataStoreRepository
import net.kasasbeh.islamicunits.screens.state.ConverterState
import net.kasasbeh.islamicunits.screens.state.rememberConverterState
import net.kasasbeh.islamicunits.unit.ConvertibleUnit
import net.kasasbeh.islamicunits.unit.ScalarUnit
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent

class ConverterScreen(
    private val title: String,
    private val units: List<ScalarUnit>,
    private val firstValue: String = "",
    private val secondValue: String = "",
    private val firstUnit: ScalarUnit = units[0],
    private val secondUnit: ScalarUnit = units[1]
) : Screen, WithTopAppBar, KoinComponent {

    constructor(title: String, unit: ConvertibleUnit) : this(
        title = title,
        units = unit.units
    )

    constructor(title: String, units: List<ScalarUnit>, favorite: Favorite) : this(
        title = title,
        units = units,
        firstValue = favorite.firstValue.toString(),
        secondValue = favorite.secondValue.toString(),
        firstUnit = favorite.firstUnit,
        secondUnit = favorite.secondUnit
    )

    init {
        require(units.size >= 2) { "There must be at least two units to convert between" }
    }

    @Composable
    override fun Content() {
        val repository: DataStoreRepository = koinInject()
        val school = repository.school.collectAsState(School.HANBALI)
        val state = rememberConverterState(
            school = school,
            units = units,
            firstValue = firstValue.toString(),
            secondValue = secondValue.toString(),
            firstUnit = firstUnit,
            secondUnit = secondUnit
        )

        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = state.first,
                    onValueChange = {
                        if (it.isBlank())
                            state.clear()
                        else
                            state.first = it
                    },
                    modifier = Modifier
                        .weight(.85f)
                        .padding(10.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next
                    ),
                    label = { Text(state.firstUnit.localizedName()) }
                )
                UnitDropDownMenu(
                    modifier = Modifier.weight(.15f),
                    onValueChange = { state.firstUnit = it }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = state.second,
                    onValueChange = {
                        if (it.isBlank())
                            state.clear()
                        else
                            state.second = it
                    },
                    modifier = Modifier
                        .weight(.85f)
                        .padding(10.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    label = { Text(state.secondUnit.localizedName()) }
                )
                UnitDropDownMenu(
                    modifier = Modifier.weight(0.15f),
                    onValueChange = { state.secondUnit = it }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                FavoriteButton(
                    state = state
                )
                CopyButton(
                    enabled = state.first.isNotEmpty() && state.second.isNotEmpty(),
                    text = "${state.first} ${state.firstUnit.localizedName()} = ${state.second} ${state.secondUnit.localizedName()}"
                )
            }
        }
    }

    @Composable
    fun FavoriteButton(state: ConverterState) {
        var showDialog by remember { mutableStateOf(false) }
        val favoritesViewModel = koinViewModel<FavoritesViewModel>()
        val favorites by favoritesViewModel.favorites.collectAsState()
        val asFavorite = state.rememberForState(favorites) {
            favorites.firstOrNull { state.matches(it) }
        }
        var showDelete by remember { mutableStateOf(false) }
        if (showDelete && asFavorite != null) {
            FavoritesDeleteDialog(
                onDismissRequest = { showDelete = false },
                onConfirm = { favoritesViewModel.removeFavorite(asFavorite) },
                favorite = asFavorite
            )
        }
        if (showDialog) {
            FavoriteDialog(
                onSubmit = { label ->
                    favoritesViewModel.addFavorite(
                        favorite = Favorite(
                            name = label,
                            firstUnit = state.firstUnit,
                            firstValue = state.first.toDouble(),
                            secondUnit = state.secondUnit,
                            secondValue = state.second.toDouble(),
                        )
                    )
                },
                onDismissRequest = { showDialog = false }
            )
        }
        FavoriteIconButton(
            onClick = {
                if (asFavorite != null)
                    showDelete = true
                else
                    showDialog = true
            },
            isFavorite = asFavorite != null,
            enabled = state.first.isNotEmpty() && state.second.isNotEmpty()
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UnitDropDownMenu(
        modifier: Modifier = Modifier,
        onValueChange: (ScalarUnit) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = modifier
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Toggle",
                    modifier = if (expanded)
                        Modifier
                    else
                        Modifier.rotate(180f)
                )
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(.5f)
            ) {
                units.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(text = unit.localizedName()) },
                        onClick = {
                            onValueChange(unit)
                            expanded = false
                        }
                    )
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun ScreenTopAppBar() {
        val navigator = LocalNavigator.currentOrThrow
        TopAppBar(title = { Text(title) }, navigationIcon = {
            IconButton(onClick = { navigator.pop() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        })
    }

    fun ConverterState.matches(favorite: Favorite): Boolean {
        val first = first.toDoubleOrNull() ?: return false
        val second = second.toDoubleOrNull() ?: return false
        return favorite.matches(first, second, firstUnit, secondUnit)
    }

}