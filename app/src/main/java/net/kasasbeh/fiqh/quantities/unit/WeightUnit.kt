package net.kasasbeh.fiqh.quantities.unit

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import net.kasasbeh.fiqh.quantities.R
import net.kasasbeh.fiqh.quantities.data.School
import net.kasasbeh.fiqh.quantities.data.schools

enum class WeightUnit(
    private val values: Map<School, Double>,
    @StringRes private val nameRes: Int
) : ScalarUnit {

    GRAM(1.0, R.string.gram),
    KILOGRAM(1000.0, R.string.kilogram),
    QIRAT(0.212, R.string.qirat),
    UQIYYAH(119.0, R.string.uqiyyah),
    SAA(2035.0, R.string.saa),
    RATL(450.0, R.string.ratl),
    WASQ(122100.0, R.string.wasq),
    MUDD(508.75, R.string.mudd);
    ;

    constructor(value: Double, @StringRes name: Int) : this(values = buildMap {
        schools().forEach { put(it, value) }
    }, name)

    constructor(
        shafii: Double,
        hanbali: Double,
        hanafi: Double,
        maliki: Double,
        @StringRes name: Int
    ) : this(values = buildMap {
        put(School.HANBALI, hanbali)
        put(School.SHAFII, shafii)
        put(School.HANAFI, hanafi)
        put(School.MALIKI, maliki)
    }, name)

    fun convertTo(school: School, quantity: Double, otherUnit: WeightUnit): Double {
        val asGrams = toGrams(school, quantity)
        return otherUnit.fromGrams(school, asGrams)
    }

    fun toGrams(school: School, quantity: Double): Double {
        return quantity * values[school]!!
    }

    fun fromGrams(school: School, quantity: Double): Double {
        return quantity / values[school]!!
    }

    override fun localizedName(context: Context): String {
        return context.getString(nameRes)
    }
}

object WeightConverter : Converter<WeightUnit> {
    override fun convert(from: WeightUnit, to: WeightUnit, school: School, value: Double): Double {
        return from.convertTo(school, value, to)
    }
}