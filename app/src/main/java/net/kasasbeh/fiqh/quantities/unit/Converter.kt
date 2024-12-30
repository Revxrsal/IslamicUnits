package net.kasasbeh.fiqh.quantities.unit

import net.kasasbeh.fiqh.quantities.data.School

interface Converter<U : ScalarUnit> {

    fun convert(
        from: U,
        to: U,
        school: School,
        value: Double
    ): Double

}