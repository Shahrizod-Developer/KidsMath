package uz.gita.kidsmath.data.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val count: Int,
    val level: Int,
    val number: Int,
    @ColumnInfo(name = "questions")
    val questionList: String,
    var state: Boolean,
    val time: Long,
    val star: Int
) : Parcelable