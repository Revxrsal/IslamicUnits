package net.kasasbeh.fiqh.quantities.unit

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import net.kasasbeh.fiqh.quantities.data.School
import net.kasasbeh.fiqh.quantities.data.schools

interface ConvertableUnit<U : ConvertableUnit<U>> {
    val units: List<ScalarUnit<U>>
}

class ScalarUnit<U : ConvertableUnit<U>>(
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

    constructor(value: Double, @StringRes name: Int) : this(
        values = buildMap { schools().forEach { put(it, value) } },
        nameRes = name
    )

    constructor(
        hanbali: Double,
        shafii: Double,
        maliki: Double,
        hanafi: Double,
        @StringRes name: Int
    ) : this(
        values = buildMap {
            put(School.HANBALI, hanbali)
            put(School.SHAFII, shafii)
            put(School.HANAFI, hanafi)
            put(School.MALIKI, maliki)
        },
        nameRes = name
    )

    fun convert(to: ScalarUnit<U>, school: School, value: Double): Double {
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
