package uz.gita.kidsmath.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.kidsmath.data.repository.AppRepository
import uz.gita.kidsmath.data.room.entity.GameEntity
import uz.gita.kidsmath.domain.usecase.LevelScreenUseCase
import javax.inject.Inject

class LevelScreenUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : LevelScreenUseCase {
    override fun getAllEasyLevel(): Flow<List<GameEntity>> = repository.getAllEasyLevel()

    override fun getAllMediumLevel(): Flow<List<GameEntity>> = repository.getAllMediumLevel()

    override fun getAllHardLevel(): Flow<List<GameEntity>> = repository.getAllHardLevel()
}