package uz.gita.kidsmath.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.kidsmath.data.room.entity.GameEntity

interface LevelScreenUseCase {


    fun getAllEasyLevel(): Flow<List<GameEntity>>

    fun getAllMediumLevel(): Flow<List<GameEntity>>

    fun getAllHardLevel(): Flow<List<GameEntity>>
}