package uz.gita.kidsmath.presentation.ui.screen.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.kidsmath.R
import uz.gita.kidsmath.databinding.ScreenSplashBinding
import uz.gita.kidsmath.presentation.ui.viewmodel.SplashScreenViewModel
import uz.gita.kidsmath.presentation.ui.viewmodel.impl.SplashScreenViewModelImpl

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {

    private val binding: ScreenSplashBinding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openMenuScreen()
    }
}