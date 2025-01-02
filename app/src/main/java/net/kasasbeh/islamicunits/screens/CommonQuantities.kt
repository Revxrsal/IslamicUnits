@file:Suppress("unused")

package net.kasasbeh.islamicunits.screens

import androidx.annotation.StringRes
import net.kasasbeh.islamicunits.unit.ScalarUnit
import net.kasasbeh.islamicunits.unit.UnitType
import net.kasasbeh.islamicunits.R
import net.kasasbeh.islamicunits.unit.DistanceUnits
import net.kasasbeh.islamicunits.unit.FinancialUnits
import net.kasasbeh.islamicunits.unit.VolumeUnits
import net.kasasbeh.islamicunits.unit.WeightUnits

private val _Quantities = mutableListOf<CommonQuantity>()
val Quantities: List<CommonQuantity> get() = _Quantities

data class CommonQuantity(
    @StringRes val name: Int,
    val unitType: UnitType,
    val primaryUnit: ScalarUnit,
    val value: Double
) {
    init {
        _Quantities.add(this)
    }
}

val TravelDistance = CommonQuantity(
    name = R.string.travel_distance,
    unitType = UnitType.DISTANCE,
    primaryUnit = DistanceUnits.FARSAKH,
    value = 16.0,
)

val NisabZakatGold = CommonQuantity(
    name = R.string.nisab_of_zakat_gold,
    unitType = UnitType.FINANCIAL,
    primaryUnit = FinancialUnits.GRAMS_OF_GOLD,
    value = 85.0
)

val NisabZakatSilver = CommonQuantity(
    name = R.string.nisab_of_zakat_silver,
    unitType = UnitType.FINANCIAL,
    primaryUnit = FinancialUnits.GRAMS_OF_SILVER,
    value = 595.0
)

val ZakatAlFitr = CommonQuantity(
    name = R.string.zakat_al_fitr,
    unitType = UnitType.WEIGHT,
    primaryUnit = WeightUnits.SAA,
    value = 1.0
)

val NisabZakatAgriculturalProduce = CommonQuantity(
    name = R.string.nisab_of_zakat_agricultural,
    unitType = UnitType.WEIGHT,
    primaryUnit = WeightUnits.WASQ,
    value = 5.0
)

val FastingFidya = CommonQuantity(
    name = R.string.fasting_fidya,
    unitType = UnitType.WEIGHT,
    primaryUnit = WeightUnits.MUDD,
    value = 1.0
)

val LargeWater = CommonQuantity(
    name = R.string.large_water,
    unitType = UnitType.VOLUME,
    primaryUnit = VolumeUnits.QULLAH,
    value = 2.0
)

val BreakingOath = CommonQuantity(
    name = R.string.breaking_oath,
    unitType = UnitType.WEIGHT,
    value = 10.0,
    primaryUnit = WeightUnits.MUDD
)

val IntercrouseDuringMenstruation = CommonQuantity(
    name = R.string.intercrouse_menstruation,
    unitType = UnitType.FINANCIAL,
    primaryUnit = FinancialUnits.DINAR,
    value = .5
)

