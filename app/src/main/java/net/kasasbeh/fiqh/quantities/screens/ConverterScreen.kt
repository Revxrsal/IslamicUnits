package net.kasasbeh.fiqh.quantities.screens

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import net.kasasbeh.fiqh.quantities.data.School
import net.kasasbeh.fiqh.quantities.icons.ContentCopy
import net.kasasbeh.fiqh.quantities.repository.DataStoreRepository
import net.kasasbeh.fiqh.quantities.screens.state.rememberConverterState
import net.kasasbeh.fiqh.quantities.unit.ConvertableUnit
import net.kasasbeh.fiqh.quantities.unit.ScalarUnit
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent

class ConverterScreen<U : ConvertableUnit<U>>(
    private val units: List<ScalarUnit<U>>,
) : Screen, WithTopAppBar, KoinComponent {

    init {
        require(units.size >= 2) { "There must be at least two units to convert between" }
    }

    @Composable
    override fun Content() {
        val repository: DataStoreRepository = koinInject()
        val school by repository.school.collectAsState(School.HANBALI)
        val state = rememberConverterState(
            school = school,
            units = units
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

                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                }
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = ContentCopy,
                        contentDescription = "Copy value"
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UnitDropDownMenu(
        modifier: Modifier = Modifier,
        onValueChange: (ScalarUnit<U>) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = modifier
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.menuAnchor()
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
        TopAppBar(title = { Text("Weights") }, navigationIcon = {
            IconButton(onClick = { navigator.push(HomeScreen) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        })
    }
}