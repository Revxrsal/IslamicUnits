package net.kasasbeh.islamicunits.data.room

import androidx.room.TypeConverter
import net.kasasbeh.islamicunits.unit.ScalarUnit
import net.kasasbeh.islamicunits.unit.scalarUnit

class UnitRoomConverter {

    @TypeConverter
    fun convertUnitToString(unit: ScalarUnit): String {
        return unit.id
    }

    @TypeConverter
    fun convertStringToUnit(string: String): ScalarUnit {
        return scalarUnit(string)
    }
}