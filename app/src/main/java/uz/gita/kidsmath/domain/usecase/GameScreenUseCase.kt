package uz.gita.kidsmath.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.kidsmath.data.model.Question
import uz.gita.kidsmath.data.room.entity.GameEntity

interface GameScreenUseCase {

    fun getByNumber(level: Int, number: Int): Flow<List<Question>>

    suspend fun saveResult(entity: GameEntity)

    suspend fun openNextLevel(level: Int, number: Int)
}