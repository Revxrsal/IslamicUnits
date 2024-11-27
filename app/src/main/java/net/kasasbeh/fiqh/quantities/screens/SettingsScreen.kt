package net.kasasbeh.fiqh.quantities.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import net.kasasbeh.fiqh.quantities.R
import net.kasasbeh.fiqh.quantities.data.School
import net.kasasbeh.fiqh.quantities.data.localizedName
import net.kasasbeh.fiqh.quantities.data.schools
import net.kasasbeh.fiqh.quantities.repository.DataStoreRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object SettingsScreen : Screen, KoinComponent, WithTopAppBar {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            LabeledSetting(
                label = stringResource(R.string.dark_theme),
                content = { ThemeSwitch() }
            )

            LabeledSetting(
                label = stringResource(R.string.school),
                content = { SchoolDropDownMenu() }
            )
        }
    }

    @Composable
    private fun LabeledSetting(
        label: String,
        content: @Composable RowScope.() -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = label
            )
            content()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SchoolDropDownMenu() {
        var expanded by remember { mutableStateOf(false) }
        val repo = get<DataStoreRepository>()
        val selectedSchool by repo.school.collectAsState(School.HANBALI)
        val scope = rememberCoroutineScope()
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(
                value = selectedSchool.localizedName(),
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Toggle",
                        modifier = if (expanded)
                            Modifier
                        else
                            Modifier.rotate(180f)
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                schools().forEach { school ->
                    DropdownMenuItem(
                        text = { Text(text = school.localizedName()) },
                        onClick = {
                            scope.launch {
                                repo.setSchool(school)
                            }
                            expanded = false
                        }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun ScreenTopAppBar() {
        val navigator = LocalNavigator.currentOrThrow
        TopAppBar(
            title = {
                Text(stringResource(R.string.settings))
            },
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

    @Composable
    private fun ThemeSwitch() {
        val repo = get<DataStoreRepository>()
        val scope = rememberCoroutineScope()
        val isDarkTheme by repo.darkTheme.collectAsState(true)
        Switch(
            modifier = Modifier.padding(10.dp),
            checked = isDarkTheme,
            onCheckedChange = {
                scope.launch {
                    repo.toggleTheme()
                }
            }
        )
    }

}