package uz.gita.kidsmath.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.kidsmath.data.room.dao.GameDao
import uz.gita.kidsmath.data.room.entity.GameEntity


@Database(entities = [GameEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): GameDao
}