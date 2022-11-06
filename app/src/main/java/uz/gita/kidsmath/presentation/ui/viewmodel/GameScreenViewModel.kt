package uz.gita.kidsmath.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.kidsmath.data.model.Question
import uz.gita.kidsmath.data.room.entity.GameEntity

interface GameScreenViewModel {

    val gameModelLiveData: Flow<List<Question>>

    fun back()

    fun getByNumber(level: Int, number: Int)

    fun saveResult(entity: GameEntity)

    fun btnClicked(state: Boolean, position: Int)

    fun openNextLevel(level: Int, number: Int)
}