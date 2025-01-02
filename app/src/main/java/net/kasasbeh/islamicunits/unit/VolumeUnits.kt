package net.kasasbeh.islamicunits.unit

import androidx.annotation.StringRes
import net.kasasbeh.islamicunits.R

object VolumeUnits : ConvertibleUnit {
    val QULLAH = unit("qullah", 95.5, R.string.qullah)

    override val units: List<ScalarUnit> = listOf(
        unit("litre", 1.0, R.string.litre),
        unit("milliletre", 1E-3, R.string.millilitre),
        QULLAH,
        unit("ritl", 0.382, R.string.ritl),
        unit("mudd", 0.6075, R.string.mudd),
        unit("saa", 2.430, R.string.saa),
    )

    private fun unit(id: String, value: Double, @StringRes name: Int): ScalarUnit {
        return ScalarUnit(id, UnitType.VOLUME, value, name)
    }
}
