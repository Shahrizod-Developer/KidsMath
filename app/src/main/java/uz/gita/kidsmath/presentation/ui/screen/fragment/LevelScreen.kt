package uz.gita.kidsmath.presentation.ui.screen.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.kidsmath.R
import uz.gita.kidsmath.databinding.DialogInfoBinding
import uz.gita.kidsmath.databinding.ScreenLevelBinding
import uz.gita.kidsmath.presentation.adapter.LevelAdapter
import uz.gita.kidsmath.presentation.ui.viewmodel.LevelScreenViewModel
import uz.gita.kidsmath.presentation.ui.viewmodel.impl.LevelScreenViewModelImpl
import uz.gita.kidsmath.presentation.utils.onClick


@AndroidEntryPoint
class LevelScreen : Fragment(R.layout.screen_level) {

    private val binding: ScreenLevelBinding by viewBinding(ScreenLevelBinding::bind)
    private val viewModel: LevelScreenViewModel by viewModels<LevelScreenViewModelImpl>()
    private val adapter: LevelAdapter by lazy { LevelAdapter() }
    private val args: LevelScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        when (args.level) {
            "easy" -> {
                binding.level.text = "Easy"
                viewModel.generateEasy()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.easyLevelsList.collectLatest {
                        adapter.submitList(it)
                    }
                }
            }
            "medium" -> {
                binding.level.text = "Medium"
                viewModel.generateMedium()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.mediumLevelsList.collectLatest {
                        adapter.submitList(it)
                    }
                }
            }
            "hard" -> {
                binding.level.text = "Hard"
                viewModel.generateHard()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.hardLevelsList.collectLatest {
                        Log.d("HHH", it.size.toString())
                        adapter.submitList(it)
                    }
                }
            }
        }

        binding.rv.adapter = adapter

        adapter.setSwitchChangedListener {
            viewModel.openGameScreen(it)
        }

        binding.info.setOnClickListener {
            it.onClick {
                showInfoDialog(args.level)
            }
        }
        binding.back.setOnClickListener {
            it.onClick {
                viewModel.back()
            }
        }
    }

    private fun showInfoDialog(text: String) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            DialogInfoBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialog.setCancelable(false)

        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(
                Color.TRANSPARENT
            )
        )

        when (text) {
            "easy" -> {
                dialogBinding.text.text =
                    "The terms of the game are as follows. In this step you will do 3 examples of mathematical operations. If you finish the whole game in 30 seconds, you get 3 stars, if you finish in 45 seconds, you get 2 stars, if you finish in 1 minute, you get 1 star and you can go to the next level. If you can't find matches within 1 minute, the game is not over, but the next level is not unlocked and stars are not awarded."
            }
            "medium" -> {

                dialogBinding.text.text =
                    "The terms of the game are as follows. In this step you will do 5 examples of mathematical operations. If you finish the whole game in 60 seconds, you get 3 stars, if you finish in 75 seconds, you get 2 stars, if you finish in 90 second, you get 1 star and you can go to the next level. If you can't find matches within 1 minute, the game is not over, but the next level is not unlocked and stars are not awarded."
            }
            "hard" -> {
                dialogBinding.text.text =
                    "The terms of the game are as follows. In this step you will do 7 examples of mathematical operations. If you finish the whole game in 90 seconds, you get 3 stars, if you finish in 105 seconds, you get 2 stars, if you finish in 2 minute, you get 1 star and you can go to the next level. If you can't find matches within 1 minute, the game is not over, but the next level is not unlocked and stars are not awarded."

            }
        }
        dialogBinding.yes.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setView(dialogBinding.root)
        dialog.show()
    }
}