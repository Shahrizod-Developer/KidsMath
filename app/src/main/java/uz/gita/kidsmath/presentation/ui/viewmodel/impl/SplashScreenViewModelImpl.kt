package uz.gita.kidsmath.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.kidsmath.presentation.navigator.Navigator
import uz.gita.kidsmath.presentation.ui.screen.fragment.SplashScreenDirections
import uz.gita.kidsmath.presentation.ui.viewmodel.SplashScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(
    private val navigator: Navigator
) : SplashScreenViewModel, ViewModel() {
    override fun openMenuScreen() {
        viewModelScope.launch {
            delay(2500)
            navigator.navigateTo(SplashScreenDirections.actionSplashScreenToMenuScreen())
        }
    }


}