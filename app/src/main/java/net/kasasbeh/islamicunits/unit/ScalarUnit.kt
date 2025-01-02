package net.kasasbeh.islamicunits.unit

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import net.kasasbeh.islamicunits.data.School
import net.kasasbeh.islamicunits.data.schools

private val unitsById by lazy {
    buildMap {
        WeightUnits.units.forEach { put(it.id, it) }
        FinancialUnits.units.forEach { put(it.id, it) }
        DistanceUnits.units.forEach { put(it.id, it) }
        VolumeUnits.units.forEach { put(it.id, it) }
    }
}

fun scalarUnitOrNull(id: String): ScalarUnit? {
    return unitsById[id]
}

fun scalarUnit(id: String): ScalarUnit {
    return unitsById[id] ?: error("Invalid unit: $id.")
}

class ScalarUnit(
    val id: String,
    val unitType: UnitType,
    @StringRes private val nameRes: Int,
    private val values: Map<School, Double>,
) {

    @Composable
    fun localizedName(): String {
        val context = LocalContext.current
        return localizedName(context)
    }

    fun localizedName(context: Context): String {
        return context.getString(nameRes)
    }

    constructor(
        id: String,
        unitType: UnitType,
        value: Double,
        @StringRes name: Int
    ) : this(
        id = id,
        unitType = unitType,
        values = buildMap { schools().forEach { put(it, value) } },
        nameRes = name
    )

    constructor(
        id: String,
        unitType: UnitType,
        hanbali: Double,
        shafii: Double,
        maliki: Double,
        hanafi: Double,
        @StringRes name: Int
    ) : this(
        unitType = unitType,
        id = id,
        values = buildMap {
            put(School.HANBALI, hanbali)
            put(School.SHAFII, shafii)
            put(School.HANAFI, hanafi)
            put(School.MALIKI, maliki)
        },
        nameRes = name
    )

    fun convert(to: ScalarUnit, school: School, value: Double): Double {
        val primary = toPrimaryUnit(school, value)
        return to.fromPrimaryUnit(school, primary)
    }

    fun toPrimaryUnit(school: School, quantity: Double): Double {
        return quantity * values[school]!!
    }

    fun fromPrimaryUnit(school: School, quantity: Double): Double {
        return quantity / values[school]!!
    }
}
