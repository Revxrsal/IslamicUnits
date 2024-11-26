package net.kasasbeh.fiqh.quantities.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.component.KoinComponent

object HomeScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        Column(modifier = Modifier.padding(10.dp)) {
        }
    }
}