package net.kasasbeh.islamicunits.screens.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import net.kasasbeh.islamicunits.data.School
import net.kasasbeh.islamicunits.unit.ConvertableUnit
import net.kasasbeh.islamicunits.unit.ScalarUnit

@Composable
fun <U : ConvertableUnit<U>> rememberConverterState(
    school: State<School>,
    units: List<ScalarUnit<U>>
) = remember {
    ConverterState(school, units)
}

class ConverterState<U : ConvertableUnit<U>>(
    private val school: State<School>,
    units: List<ScalarUnit<U>>
) {

    private var firstValue by mutableStateOf("")
    private var secondValue by mutableStateOf("")

    private var _firstUnit by mutableStateOf(units[0])
    private var _secondUnit by mutableStateOf(units[1])

    var firstUnit: ScalarUnit<U>
        get() = _firstUnit
        set(value) = updateFirstUnit(value)

    var secondUnit: ScalarUnit<U>
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

    private fun updateFirstUnit(value: ScalarUnit<U>) {
        _firstUnit = value
        val v = secondValue.toDoubleOrNull()
        if (v != null) {
            firstValue = "%.3f".format(secondUnit.convert(firstUnit, school.value, v))
        }
    }

    private fun updateSecondUnit(value: ScalarUnit<U>) {
        _secondUnit = value
        val v = firstValue.toDoubleOrNull()
        if (v != null) {
            secondValue = "%.3f".format(firstUnit.convert(secondUnit, school.value, v))
        }
    }

    init {
        require(units.size >= 2) { "There must be at least two units" }
    }
}