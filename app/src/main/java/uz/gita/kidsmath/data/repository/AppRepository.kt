package uz.gita.kidsmath.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.kidsmath.data.model.Question
import uz.gita.kidsmath.data.room.entity.GameEntity

interface AppRepository {

    fun getAllEasyLevel(): Flow<List<GameEntity>>

    fun getAllMediumLevel(): Flow<List<GameEntity>>

    fun getAllHardLevel(): Flow<List<GameEntity>>

    fun update(entity: GameEntity)

    suspend fun generateQuestion()

    fun getLevel(): Flow<String>

    suspend fun openNextLevel(level: Int, number: Int)

    suspend fun setLevel(level: String)

    fun getByNumber(level: Int, number: Int): Flow<List<Question>>
}