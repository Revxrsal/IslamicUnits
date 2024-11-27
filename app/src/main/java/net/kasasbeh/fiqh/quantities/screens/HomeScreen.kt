package net.kasasbeh.fiqh.quantities.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import net.kasasbeh.fiqh.quantities.icons.Weight
import org.koin.core.component.KoinComponent

object HomeScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
        ) {
            Category("Weights") {
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
            }
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(5.dp))
            Category("Weights") {
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
            }
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(5.dp))
            Category("Weights") {
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
            }
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(5.dp))
            Category("Weights") {
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
                item { Quantity() }
            }
        }
    }

    @Composable
    private fun Category(
        title: String,
        content: LazyGridScope.() -> Unit = {}
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(10.dp)
        ) {
            content()
        }
    }

    @Composable
    fun Quantity() {
        Card(
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 3.dp,
            ),
            onClick = { },
            modifier = Modifier.padding(10.dp),
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
}