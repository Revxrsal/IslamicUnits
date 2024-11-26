package net.kasasbeh.fiqh.quantities.components.parent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import net.kasasbeh.fiqh.quantities.R
import net.kasasbeh.fiqh.quantities.screens.SettingsScreen
import net.kasasbeh.fiqh.quantities.screens.WithTopAppBar

@Composable
fun AppScaffold(content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            AppBar()
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                content()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    val navigator = LocalNavigator.currentOrThrow
    val screen = navigator.lastItem
    if (screen is WithTopAppBar) {
        screen.ScreenTopAppBar()
    } else {
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