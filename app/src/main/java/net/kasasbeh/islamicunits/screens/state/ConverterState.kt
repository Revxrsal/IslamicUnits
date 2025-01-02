package net.kasasbeh.islamicunits.screens.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import net.kasasbeh.islamicunits.data.School
import net.kasasbeh.islamicunits.data.room.Favorite
import net.kasasbeh.islamicunits.unit.ScalarUnit

@Composable
fun rememberConverterState(
    school: State<School>,
    units: List<ScalarUnit>,
    firstValue: String = "",
    secondValue: String = "",
    firstUnit: ScalarUnit = units[0],
    secondUnit: ScalarUnit = units[1]
) = remember {
    ConverterState(school, units, firstValue, secondValue, firstUnit, secondUnit)
}

class ConverterState(
    private val school: State<School>,
    units: List<ScalarUnit>,
    firstValue: String,
    secondValue: String,
    firstUnit: ScalarUnit,
    secondUnit: ScalarUnit
) {

    private var firstValue by mutableStateOf(firstValue)
    private var secondValue by mutableStateOf(secondValue)

    private var _firstUnit by mutableStateOf(firstUnit)
    private var _secondUnit by mutableStateOf(secondUnit)

    var firstUnit: ScalarUnit
        get() = _firstUnit
        set(value) = updateFirstUnit(value)

    var secondUnit: ScalarUnit
        get() = _secondUnit
        set(value) = updateSecondUnit(value)

    var first: String
        get() = firstValue
        set(value) = updateFirst(value)

    var second: String
        get() = secondValue
        set(value) = updateSecond(value)

    fun clear() {
        firstValue = ""
        secondValue = ""
    }

    private fun updateFirst(newValue: String) {
        val number = newValue.toDoubleOrNull()
        if (number != null && number >= 0) {
            firstValue = newValue
            val converted = firstUnit.convert(secondUnit, school.value, number)
            secondValue = "%.3f".format(converted)
        }
    }

    private fun updateSecond(newValue: String) {
        val number = newValue.toDoubleOrNull()
        if (number != null && number >= 0) {
            secondValue = newValue
            val converted = secondUnit.convert(firstUnit, school.value, number)
            firstValue = "%.3f".format(converted)
        }
    }

    private fun updateFirstUnit(value: ScalarUnit) {
        _firstUnit = value
        val v = secondValue.toDoubleOrNull()
        if (v != null) {
            firstValue = "%.3f".format(secondUnit.convert(firstUnit, school.value, v))
        }
    }

    private fun updateSecondUnit(value: ScalarUnit) {
        _secondUnit = value
        val v = firstValue.toDoubleOrNull()
        if (v != null) {
            secondValue = "%.3f".format(firstUnit.convert(secondUnit, school.value, v))
        }
    }

    @Composable
    inline fun <T> rememberForState(
        favorites: List<Favorite>,
        crossinline calculation: @DisallowComposableCalls () -> T
    ): T = remember(
        first, second, firstUnit, secondUnit, favorites
    ) {
        calculation()
    }

    init {
        require(units.size >= 2) { "There must be at least two units" }
    }
}