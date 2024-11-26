package net.kasasbeh.fiqh.quantities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.CrossfadeTransition
import net.kasasbeh.fiqh.quantities.components.parent.AppScaffold
import net.kasasbeh.fiqh.quantities.screens.HomeScreen
import net.kasasbeh.fiqh.quantities.ui.theme.FiqhQuantitiesTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                FiqhQuantitiesTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Navigator(screen = HomeScreen) { navigator ->
                            CrossfadeTransition(navigator) { s ->
                                AppScaffold { s.Content() }
                            }
                        }
                    }
                }
            }
        }
    }
}
