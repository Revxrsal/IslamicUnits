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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import net.kasasbeh.fiqh.quantities.R
import net.kasasbeh.fiqh.quantities.icons.Weight

object HomeScreen : Screen, WithTopAppBar {

    @Composable
    override fun Content() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Category("Weights") {
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
            }
            Category("Weights") {
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
            }
            Category("Weights") {
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
            }
            Category("Weights") {
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
            }
            Category("Weights") {
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
                Section {
                    Quantity()
                    Quantity()
                }
            }

            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(5.dp))
        }
    }

    @Composable
    private fun Section(content: @Composable RowScope.() -> Unit) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            content()
        }
    }

    @Composable
    private fun Category(
        title: String,
        content: @Composable () -> Unit = {}
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            content()
        }
    }

    @Composable
    fun RowScope.Quantity() {
        Card(
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 3.dp,
            ),
            onClick = { },
            modifier = Modifier.padding(10.dp).weight(1f),
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Weight,
                    contentDescription = "Weight",
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "Weight",
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