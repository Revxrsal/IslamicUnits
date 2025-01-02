package net.kasasbeh.islamicunits.unit

import androidx.annotation.StringRes
import net.kasasbeh.islamicunits.R

object WeightUnits : ConvertibleUnit {

    val WASQ = unit("wasq", 122200.0, R.string.wasq)
    val MUDD = unit("muddw", 607.5, R.string.mudd)
    val SAA = unit("saa", 2036.0, R.string.saa)

    override val units: List<ScalarUnit> = listOf(
        unit("kilogram", 1000.0, R.string.kilogram),
        unit("gram", 1.0, R.string.gram),
        unit("ratlw", 382.0, R.string.ritl),
        SAA,
//        unit("mithqal", 4.25, R.string.mithqal),
        WASQ,
//        unit("dirham", 2.97, R.string.dirham),
        MUDD,
        unit("qullahw", 95500.0, R.string.qullah),
    )

    private fun unit(id: String, value: Double, @StringRes name: Int): ScalarUnit {
        return ScalarUnit(id, UnitType.WEIGHT, value, name)
    }
}