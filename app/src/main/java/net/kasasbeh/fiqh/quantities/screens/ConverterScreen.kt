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
import net.kasasbeh.fiqh.quantities.icons.Swap
import net.kasasbeh.fiqh.quantities.repository.DataStoreRepository
import net.kasasbeh.fiqh.quantities.unit.Converter
import net.kasasbeh.fiqh.quantities.unit.ScalarUnit
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent

class ConverterScreen<U : ScalarUnit>(
    private val units: List<U>,
    private val converter: Converter<U>,
) : Screen, WithTopAppBar, KoinComponent {

    init {
        require(units.size >= 2) { "There must be at least two units to convert between" }
    }

    @Composable
    override fun Content() {
        var firstUnit by remember { mutableStateOf(units[0]) }
        var secondUnit by remember { mutableStateOf(units[1]) }
        var firstStr by remember { mutableStateOf("") }
        var secondStr by remember { mutableStateOf("") }
        val repository: DataStoreRepository = koinInject()
        val school by repository.school.collectAsState(School.HANBALI)

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
                    value = firstStr,
                    onValueChange = {
                        if (it.isBlank()) firstStr = ""
                        else {
                            val number = it.toDoubleOrNull()
                            if (number != null && number >= 0) {
                                firstStr = it
                                secondStr =
                                    "%.4f".format(
                                        converter.convert(
                                            firstUnit,
                                            secondUnit,
                                            school,
                                            number
                                        )
                                    )
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(.85f)
                        .padding(10.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next
                    ),
                    label = { Text(firstUnit.localizedName()) }
                )
                UnitDropDownMenu(
                    modifier = Modifier.weight(.15f),
                    onValueChange = {
                        val oldUnit = firstUnit
                        firstUnit = it
                        val v = firstStr.toDoubleOrNull()
                        if (v != null)
                            firstStr =
                                "%.4f".format(converter.convert(oldUnit, firstUnit, school, v))
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = secondStr,
                    onValueChange = {
                        if (it.isBlank()) {
                            secondStr = ""
                            firstStr = ""
                        } else {
                            val number = it.toDoubleOrNull()
                            if (number != null && number >= 0) {
                                secondStr = it
                                firstStr =
                                    converter.convert(secondUnit, firstUnit, school, number)
                                        .toString()
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(.85f)
                        .padding(10.dp)
                        .fillMaxWidth(), keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    label = { Text(secondUnit.localizedName()) }
                )
                UnitDropDownMenu(
                    modifier = Modifier.weight(0.15f),
                    onValueChange = {
                        val oldUnit = secondUnit
                        secondUnit = it
                        val v = firstStr.toDoubleOrNull()
                        if (v != null)
                            secondStr =
                                "%.4f".format(converter.convert(oldUnit, secondUnit, school, v))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        val tempUnit = firstUnit
                        firstUnit = secondUnit
                        secondUnit = tempUnit
                        val tempV = firstStr
                        firstStr = tempV
                        val v = tempV.toDoubleOrNull()
                        if (v != null)
                            secondStr =
                                "%.4f".format(converter.convert(firstUnit, secondUnit, school, v))
                    }
                ) {
                    Icon(
                        imageVector = Swap,
                        contentDescription = "Swap units"
                    )
                }
                IconButton(
                    onClick = {
                        val temp = firstUnit
                        firstUnit = secondUnit
                        secondUnit = temp
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Swap units"
                    )
                }
                IconButton(
                    onClick = {
                        val temp = firstUnit
                        firstUnit = secondUnit
                        secondUnit = temp
                    }
                ) {
                    Icon(
                        imageVector = ContentCopy,
                        contentDescription = "Swap units"
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UnitDropDownMenu(
        modifier: Modifier = Modifier,
        onValueChange: (U) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = modifier
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.menuAnchor(),

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