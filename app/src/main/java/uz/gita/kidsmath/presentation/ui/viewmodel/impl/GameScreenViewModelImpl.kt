package uz.gita.kidsmath.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.kidsmath.data.model.Question
import uz.gita.kidsmath.data.room.entity.GameEntity
import uz.gita.kidsmath.domain.usecase.GameScreenUseCase
import uz.gita.kidsmath.presentation.navigator.Navigator
import uz.gita.kidsmath.presentation.ui.viewmodel.GameScreenViewModel
import uz.gita.kidsmath.presentation.utils.eventFlow
import javax.inject.Inject


@HiltViewModel
class GameScreenViewModelImpl @Inject constructor(
    private val useCase: GameScreenUseCase,
    private val navigator: Navigator
) :
    GameScreenViewModel, ViewModel() {

    override val gameModelLiveData = eventFlow<List<Question>>()
    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun getByNumber(level: Int, number: Int) {
        viewModelScope.launch {
            useCase.getByNumber(level, number).collect {
                gameModelLiveData.emit(it)
            }
        }
    }

    override fun saveResult(entity: GameEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.saveResult(entity)
        }
    }

    override fun btnClicked(state: Boolean, position: Int) {

    }

    override fun openNextLevel(level: Int, number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TTT", "Salom")
            useCase.openNextLevel(level, number)
        }
    }
}