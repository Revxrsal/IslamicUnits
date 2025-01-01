package net.kasasbeh.islamicunits.unit

import net.kasasbeh.islamicunits.R

// done
object VolumeUnits : ConvertableUnit<VolumeUnits> {
    override val units: List<ScalarUnit<VolumeUnits>> = listOf(
        ScalarUnit(1.0, R.string.litre),
        ScalarUnit(1E-3, R.string.millilitre),
        ScalarUnit(95.5, R.string.qullah),
        ScalarUnit(0.382, R.string.ritl),
        ScalarUnit(0.6075, R.string.mudd),
        ScalarUnit(2.430, R.string.saa),
    )
}
