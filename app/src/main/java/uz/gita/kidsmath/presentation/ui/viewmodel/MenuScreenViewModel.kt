package uz.gita.kidsmath.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow

interface MenuScreenViewModel {

    val levelFlow: Flow<String>

    val showLevelButton: Flow<Boolean>
    val showSettingButton: Flow<Boolean>
    val showExitButton: Flow<Boolean>
    val showInfoButton: Flow<Boolean>

    suspend fun setLevel(level: String)

    fun onClickEasy(level: String)

    fun onClickMedium(level: String)

    fun onClickHard(level: String)

    fun onCLickPlayNowButton(level: String)

    fun onClickLevelButton()

    fun onClickSettingButton()

    fun onClickExitButton()

    fun generate()

    fun onClickInfoButton()
}