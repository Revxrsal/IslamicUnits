package net.kasasbeh.islamicunits.unit

import net.kasasbeh.islamicunits.R

object MoneyUnits : ConvertableUnit<MoneyUnits> {
    override val units: List<ScalarUnit<MoneyUnits>> = listOf(
        ScalarUnit(1.0, R.string.dinar),
        ScalarUnit(.1, R.string.dirham),
        ScalarUnit(1/4.25, R.string.gold_gram),
        ScalarUnit(1/29.75, R.string.silver_gram),
    )
}
