package uz.gita.kidsmath.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.kidsmath.data.room.entity.GameEntity

interface LevelScreenViewModel {

    val easyLevelsList: Flow<List<GameEntity>>
    val mediumLevelsList: Flow<List<GameEntity>>
    val hardLevelsList: Flow<List<GameEntity>>

    fun back()

    fun openGameScreen(gameEntity: GameEntity)

    fun generateEasy()

    fun generateMedium()

    fun generateHard()
}