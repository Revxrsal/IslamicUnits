package net.kasasbeh.islamicunits.unit

import net.kasasbeh.islamicunits.R

// done
object DistanceUnits : ConvertableUnit<DistanceUnits> {
    override val units: List<ScalarUnit<DistanceUnits>> = listOf(
        ScalarUnit(1.0, R.string.metre),
        ScalarUnit(1000.0, R.string.kilometre),
        ScalarUnit(1609.34, R.string.mile),
        ScalarUnit(0.48, R.string.thiraa),
        ScalarUnit(0.212, R.string.finger),
        ScalarUnit(34560.0, R.string.bareed),
        ScalarUnit(
            hanbali = 8640.0,
            hanafi = 5040.0,
            shafii = 5040.0,
            maliki = 5040.0,
            name = R.string.farsakh
        ),
        ScalarUnit(2880.0, R.string.meel),
    )
}
