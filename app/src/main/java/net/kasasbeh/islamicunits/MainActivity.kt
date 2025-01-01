package net.kasasbeh.islamicunits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.CrossfadeTransition
import net.kasasbeh.islamicunits.components.AppScaffold
import net.kasasbeh.islamicunits.screens.HomeScreen
import net.kasasbeh.islamicunits.ui.theme.IslamicUnitsTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                IslamicUnitsTheme {
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
