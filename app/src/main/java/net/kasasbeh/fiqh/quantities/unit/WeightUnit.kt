package net.kasasbeh.fiqh.quantities.unit

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import net.kasasbeh.fiqh.quantities.R

enum class WeightUnit(
    private val grams: Double,
    @StringRes private val nameRes: Int
) {

    GRAM(1.0, R.string.app_name),
    KILOGRAM(1000.0, R.string.kilogram),
    QIRAT(0.212, R.string.qirat),
    UQIYYAH(119.0, R.string.uqiyyah),
    SAA(2035.0, R.string.saa),
    RATL(450.0, R.string.ratl),
    WASQ(122100.0, R.string.wasq),
    MUDD(508.75, R.string.mudd);
;
    fun convertTo(quantity: Double, otherUnit: WeightUnit): Double {
        val asGrams = toGrams(quantity)
        return otherUnit.fromGrams(asGrams)
    }

    fun toGrams(quantity: Double): Double {
        return quantity * grams
    }

    fun fromGrams(quantity: Double): Double {
        return quantity / grams
    }

    @Composable
    fun localizedName(): String {
        return stringResource(nameRes)
    }

    fun localizedName(context: Context): String {
        return context.getString(nameRes)
    }
}