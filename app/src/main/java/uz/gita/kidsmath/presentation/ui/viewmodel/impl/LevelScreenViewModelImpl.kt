package uz.gita.kidsmath.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.kidsmath.data.room.entity.GameEntity
import uz.gita.kidsmath.domain.usecase.LevelScreenUseCase
import uz.gita.kidsmath.presentation.navigator.Navigator
import uz.gita.kidsmath.presentation.ui.screen.fragment.LevelScreenDirections
import uz.gita.kidsmath.presentation.ui.viewmodel.LevelScreenViewModel
import uz.gita.kidsmath.presentation.utils.eventFlow
import javax.inject.Inject


@HiltViewModel
class LevelScreenViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val useCase: LevelScreenUseCase
) : LevelScreenViewModel, ViewModel() {
    override val easyLevelsList = eventFlow<List<GameEntity>>()
    override val mediumLevelsList = eventFlow<List<GameEntity>>()
    override val hardLevelsList = eventFlow<List<GameEntity>>()

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun openGameScreen(gameEntity: GameEntity) {
        viewModelScope.launch {
            navigator.navigateTo(LevelScreenDirections.actionLevelScreenToGameScreen(gameEntity))
        }
    }

    override fun generateEasy() {
        viewModelScope.launch {
            useCase.getAllEasyLevel().collectLatest {
                easyLevelsList.emit(it)
            }
        }

    }

    override fun generateMedium() {
        viewModelScope.launch {
            useCase.getAllMediumLevel().collectLatest {
                mediumLevelsList.emit(it)
            }
        }
    }

    override fun generateHard() {
        viewModelScope.launch {
            useCase.getAllHardLevel().collectLatest {
                Log.d("HHH", it.size.toString())
                hardLevelsList.emit(it)
            }
        }
    }
}