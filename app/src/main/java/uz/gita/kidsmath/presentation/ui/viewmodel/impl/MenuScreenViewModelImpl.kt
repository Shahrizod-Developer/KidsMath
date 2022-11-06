package uz.gita.kidsmath.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.kidsmath.domain.usecase.MenuScreenUseCase
import uz.gita.kidsmath.presentation.navigator.Navigator
import uz.gita.kidsmath.presentation.ui.screen.fragment.MenuScreenDirections
import uz.gita.kidsmath.presentation.ui.viewmodel.MenuScreenViewModel
import uz.gita.kidsmath.presentation.utils.eventFlow
import javax.inject.Inject

@HiltViewModel
class MenuScreenViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val useCase: MenuScreenUseCase
) : MenuScreenViewModel, ViewModel() {

    override val levelFlow = eventFlow<String>()
    override val showLevelButton = eventFlow<Boolean>()
    override val showSettingButton = eventFlow<Boolean>()
    override val showExitButton = eventFlow<Boolean>()
    override val showInfoButton = eventFlow<Boolean>()

    override suspend fun setLevel(level: String) = useCase.setLevel(level)

    init {
        viewModelScope.launch {
            useCase.getLevel().collectLatest {
                levelFlow.emit(it)
            }
        }
    }

    override fun onClickEasy(level: String) {
        viewModelScope.launch {
            levelFlow.emit("easy")
        }
    }

    override fun onClickMedium(level: String) {
        viewModelScope.launch {
            levelFlow.emit("medium")
        }
    }

    override fun onClickHard(level: String) {
        viewModelScope.launch {
            levelFlow.emit("hard")
        }
    }

    override fun onCLickPlayNowButton(level: String) {
        viewModelScope.launch {
            navigator.navigateTo(MenuScreenDirections.actionMenuScreenToLevelScreen(level))
        }
    }

    override fun onClickLevelButton() {
        viewModelScope.launch {
            showLevelButton.emit(true)
        }
    }

    override fun onClickSettingButton() {
        viewModelScope.launch {
            showSettingButton.emit(true)
        }
    }

    override fun onClickExitButton() {
        viewModelScope.launch {
            showExitButton.emit(true)
        }
    }

    override fun generate() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.generate()
        }

    }

    override fun onClickInfoButton() {
        viewModelScope.launch {
            showInfoButton.emit(true)
        }
    }
}