package net.kasasbeh.fiqh.quantities.unit

import net.kasasbeh.fiqh.quantities.R

// done
object VolumeUnit : ConvertableUnit<VolumeUnit> {
    override val units: List<ScalarUnit<VolumeUnit>> = listOf(
        ScalarUnit(1.0, R.string.litre),
        ScalarUnit(1E-3, R.string.millilitre),
        ScalarUnit(95.5, R.string.qullah),
        ScalarUnit(0.382, R.string.ritl),
        ScalarUnit(0.6075, R.string.mudd),
        ScalarUnit(2.430, R.string.saa),
    )
}
