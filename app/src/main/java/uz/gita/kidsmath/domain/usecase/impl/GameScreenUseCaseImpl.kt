package uz.gita.kidsmath.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.kidsmath.data.model.Question
import uz.gita.kidsmath.data.repository.AppRepository
import uz.gita.kidsmath.data.room.entity.GameEntity
import uz.gita.kidsmath.domain.usecase.GameScreenUseCase
import javax.inject.Inject

class GameScreenUseCaseImpl @Inject constructor(private val repository: AppRepository) :
    GameScreenUseCase {
    override fun getByNumber(level: Int, number: Int): Flow<List<Question>> =
        repository.getByNumber(level, number)

    override suspend fun saveResult(entity: GameEntity) = repository.update(entity)

    override suspend fun openNextLevel(level: Int, number: Int) =
        repository.openNextLevel(level, number)

}