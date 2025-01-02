package net.kasasbeh.islamicunits.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.kasasbeh.islamicunits.unit.ConvertibleUnit
import net.kasasbeh.islamicunits.unit.ScalarUnit
import java.util.UUID

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val firstValue: Double,
    val secondValue: Double,
    val firstUnit: ScalarUnit,
    val secondUnit: ScalarUnit
) {

    fun matches(
        firstValue: Double,
        secondValue: Double,
        firstUnit: ScalarUnit,
        secondUnit: ScalarUnit
    ): Boolean {
        return this.firstValue == firstValue &&
                this.firstUnit == firstUnit &&
                this.secondValue == secondValue &&
                this.secondUnit == secondUnit
    }

}