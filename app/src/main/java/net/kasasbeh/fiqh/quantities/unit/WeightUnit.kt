package net.kasasbeh.fiqh.quantities.unit

import net.kasasbeh.fiqh.quantities.R

// done
object WeightUnit : ConvertableUnit<WeightUnit> {
    override val units: List<ScalarUnit<WeightUnit>> = listOf(
        ScalarUnit(1.0, R.string.gram),
        ScalarUnit(1000.0, R.string.kilogram),
        ScalarUnit(382.0, R.string.ritl),
        ScalarUnit(2036.0, R.string.saa),
        ScalarUnit(122200.0, R.string.wasq),
        ScalarUnit(4.25, R.string.mithqal),
        ScalarUnit(2.97, R.string.dirham),
        ScalarUnit(607.5, R.string.mudd),
        ScalarUnit(95500.0, R.string.qullah),
    )

}