package uz.gita.kidsmath.data.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    fun insert(t: List<T>)

    @Update
    fun update(entity: T)

    @Delete
    fun delete(entity: T)
}