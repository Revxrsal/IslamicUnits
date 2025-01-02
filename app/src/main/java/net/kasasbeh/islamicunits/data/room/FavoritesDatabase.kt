package net.kasasbeh.islamicunits.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Favorite::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UnitRoomConverter::class)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun dao(): FavoritesDao
}
