package net.kasasbeh.fiqh.quantities.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import net.kasasbeh.fiqh.quantities.R
import net.kasasbeh.fiqh.quantities.icons.Food
import net.kasasbeh.fiqh.quantities.icons.Landscape
import net.kasasbeh.fiqh.quantities.icons.Money
import net.kasasbeh.fiqh.quantities.icons.Ruler
import net.kasasbeh.fiqh.quantities.icons.Volume
import net.kasasbeh.fiqh.quantities.icons.Weight
import net.kasasbeh.fiqh.quantities.unit.AreaUnit
import net.kasasbeh.fiqh.quantities.unit.DistanceUnit
import net.kasasbeh.fiqh.quantities.unit.FoodUnit
import net.kasasbeh.fiqh.quantities.unit.MoneyUnit
import net.kasasbeh.fiqh.quantities.unit.VolumeUnit
import net.kasasbeh.fiqh.quantities.unit.WeightUnit

object HomeScreen : Screen, WithTopAppBar {

    @Composable
    override fun Content() {
        val scrollState = rememberScrollState()
        val search = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = search.value,
                onValueChange = { search.value = it },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                label = { Text("Search for units") },
                leadingIcon = { Icon(Icons.Filled.Search, "Search") }
            )
            Section {
                UnitCategory(stringResource(R.string.weight), Weight) {
                    ConverterScreen(
                        units = WeightUnit.units,
                        title = it
                    )
                }
                UnitCategory(stringResource(R.string.volume), Volume) {
                    ConverterScreen(
                        units = VolumeUnit.units,
                        title = it
                    )
                }
            }
            Section {
                UnitCategory(stringResource(R.string.distance), Ruler) {
                    ConverterScreen(
                        units = DistanceUnit.units,
                        title = it
                    )
                }
//                UnitCategory("Area", Landscape) {
//                    ConverterScreen(
//                        units = AreaUnit.units
//                    )
//                }
            }
//            Section {
//                UnitCategory("Money", Money) {
//                    ConverterScreen(
//                        units = MoneyUnit.units
//                    )
//                }
//                UnitCategory("Food", Food) {
//                    ConverterScreen(
//                        units = FoodUnit.units
//                    )
//                }
//            }
        }
    }

    @Composable
    private fun Section(content: @Composable RowScope.() -> Unit) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            content()
        }
    }

    @Composable
    fun RowScope.UnitCategory(
        title: String,
        icon: ImageVector,
        targetScreen: (title: String) -> Screen
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Card(
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 3.dp,
            ),
            onClick = { navigator.push(targetScreen(title)) },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun ScreenTopAppBar() {
        val navigator = LocalNavigator.currentOrThrow
        TopAppBar(
            title = {
                Text(stringResource(R.string.app_name))
            },
            navigationIcon = {
                IconButton(onClick = { navigator.push(SettingsScreen) }) {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        )
    }
}