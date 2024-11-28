package net.kasasbeh.fiqh.quantities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
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
