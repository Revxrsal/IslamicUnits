package net.kasasbeh.islamicunits.unit

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import net.kasasbeh.islamicunits.R
import net.kasasbeh.islamicunits.icons.Money
import net.kasasbeh.islamicunits.icons.Ruler
import net.kasasbeh.islamicunits.icons.Volume
import net.kasasbeh.islamicunits.icons.Weight

enum class UnitType(
    @StringRes val title: Int,
    val getUnits: () -> List<ScalarUnit>,
    val icon: ImageVector,
) {
    DISTANCE(R.string.distance, { DistanceUnits.units }, Ruler),
    VOLUME(R.string.volume, { VolumeUnits.units }, Volume),
    WEIGHT(R.string.weight, { WeightUnits.units }, Weight),
    FINANCIAL(R.string.finances, { FinancialUnits.units }, Money);

    @Composable
    fun title(): String {
        return stringResource(title)
    }

}