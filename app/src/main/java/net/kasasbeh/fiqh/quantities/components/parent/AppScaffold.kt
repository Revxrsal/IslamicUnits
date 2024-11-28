package net.kasasbeh.fiqh.quantities.components.parent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import net.kasasbeh.fiqh.quantities.screens.HomeScreen
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
        HomeScreen.ScreenTopAppBar()
    }
}