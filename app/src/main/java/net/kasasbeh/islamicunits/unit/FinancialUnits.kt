package net.kasasbeh.islamicunits.unit

import androidx.annotation.StringRes
import net.kasasbeh.islamicunits.R

object FinancialUnits : ConvertibleUnit {

    val GRAMS_OF_GOLD = unit("grams_of_gold", 1 / 4.25, R.string.gold_gram)
    val GRAMS_OF_SILVER = unit("grams_of_silver", 1 / 29.75, R.string.silver_gram)
    val DINAR = unit("dinar", 1.0, R.string.dinar)

    override val units: List<ScalarUnit> = listOf(
        DINAR,
        unit("dirham", .1, R.string.dirham),
        GRAMS_OF_GOLD,
        GRAMS_OF_SILVER,
    )

    private fun unit(id: String, value: Double, @StringRes name: Int): ScalarUnit {
        return ScalarUnit(id, UnitType.FINANCIAL, value, name)
    }
}
