package net.kasasbeh.islamicunits.unit

import net.kasasbeh.islamicunits.R

object FoodUnit : ConvertableUnit<FoodUnit> {
    override val units: List<ScalarUnit<FoodUnit>> = listOf(
        ScalarUnit(1.0, R.string.gram),
        ScalarUnit(1000.0, R.string.kilogram),
        ScalarUnit(0.212, R.string.qirat),
        ScalarUnit(119.0, R.string.uqiyyah),
        ScalarUnit(2035.0, R.string.saa),
        ScalarUnit(450.0, R.string.ritl),
        ScalarUnit(122100.0, R.string.wasq),
        ScalarUnit(508.75, R.string.mudd),
    )
}
