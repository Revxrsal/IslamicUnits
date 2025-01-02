package net.kasasbeh.islamicunits.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import net.kasasbeh.islamicunits.R
import net.kasasbeh.islamicunits.data.School
import net.kasasbeh.islamicunits.icons.Money
import net.kasasbeh.islamicunits.icons.Ruler
import net.kasasbeh.islamicunits.icons.Volume
import net.kasasbeh.islamicunits.icons.Weight
import net.kasasbeh.islamicunits.repository.DataStoreRepository
import net.kasasbeh.islamicunits.unit.DistanceUnits
import net.kasasbeh.islamicunits.unit.FinancialUnits
import net.kasasbeh.islamicunits.unit.VolumeUnits
import net.kasasbeh.islamicunits.unit.WeightUnits
import org.koin.compose.koinInject

object HomeScreen : Screen, WithTopAppBar {

    @Composable
    override fun Content() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.converters),
                modifier = Modifier.padding(10.dp),
            )
            Section {
                UnitCategory(stringResource(R.string.weight), Weight) {
                    ConverterScreen(
                        unit = WeightUnits,
                        title = it
                    )
                }
                UnitCategory(stringResource(R.string.volume), Volume) {
                    ConverterScreen(
                        unit = VolumeUnits,
                        title = it
                    )
                }
            }
            Section {
                UnitCategory(stringResource(R.string.distance), Ruler) {
                    ConverterScreen(
                        unit = DistanceUnits,
                        title = it
                    )
                }
                UnitCategory(stringResource(R.string.finances), Money) {
                    ConverterScreen(
                        unit = FinancialUnits,
                        title = it
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(10.dp),
                thickness = 1.dp
            )
            CommonQuantities()
        }
    }

    @Composable
    @OptIn(ExperimentalLayoutApi::class)
    private fun ColumnScope.CommonQuantities() {
        val repo = koinInject<DataStoreRepository>()
        val school by repo.school.collectAsState(School.HANBALI)
        Text(
            text = stringResource(R.string.common_quantities),
            modifier = Modifier.padding(10.dp),
        )
        FlowRow(
            Modifier
                .fillMaxWidth(1f)
                .padding(10.dp)
                .wrapContentHeight(align = Alignment.Top),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            for (quantity in Quantities) {
                ListCommonQuantity(quantity, school)
            }
        }
    }

    @Composable
    fun ListCommonQuantity(
        quantity: CommonQuantity,
        school: School,
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val title = quantity.primaryUnit.unitType.title()
        val secondUnit = remember {
            quantity.primaryUnit
                .unitType
                .getUnits()
                .first { it != quantity.primaryUnit }
        }
        Card(
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 3.dp,
            ),
            onClick = {
                navigator.push(
                    ConverterScreen(
                        title = title,
                        units = quantity.primaryUnit.unitType.getUnits(),
                        firstUnit = quantity.primaryUnit,
                        secondUnit = secondUnit,
                        firstValue = quantity.value.toString(),
                        secondValue = quantity.primaryUnit.convert(
                            to = secondUnit,
                            value = quantity.value,
                            school = school
                        ).toString()
                    )
                )
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = quantity.unitType.icon,
                    contentDescription = "Quantity icon",
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = stringResource(quantity.name))
            }
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
        targetScreen: (title: String) -> Screen,
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
            },
            actions = {
                IconButton(onClick = { navigator.push(FavoritesScreen) }) {
                    Icon(
                        Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorites"
                    )
                }
            }
        )
    }
}