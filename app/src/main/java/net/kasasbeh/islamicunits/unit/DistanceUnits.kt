package net.kasasbeh.islamicunits.unit

import androidx.annotation.StringRes
import net.kasasbeh.islamicunits.R

object DistanceUnits : ConvertibleUnit {

    val FARSAKH = ScalarUnit(
        id = "farsakh",
        unitType = UnitType.DISTANCE,
        hanbali = 8640.0,
        hanafi = 5040.0,
        shafii = 5040.0,
        maliki = 5040.0,
        name = R.string.farsakh
    )

    override val units: List<ScalarUnit> = listOf(
        unit("kilometre", 1000.0, R.string.kilometre),
        unit("metre", 1.0, R.string.metre),
        unit("mile", 1609.34, R.string.mile),
        unit("dira", 0.48, R.string.thiraa),
        unit("finger", 0.212, R.string.finger),
        unit("bareed", 34560.0, R.string.bareed),
        FARSAKH,
        unit("meel", 2880.0, R.string.meel),
    )

    private fun unit(id: String, value: Double, @StringRes name: Int): ScalarUnit {
        return ScalarUnit(id, UnitType.DISTANCE, value, name)
    }
}
