package uz.gita.kidsmath.domain.di

import androidx.room.Insert
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.kidsmath.domain.usecase.GameScreenUseCase
import uz.gita.kidsmath.domain.usecase.LevelScreenUseCase
import uz.gita.kidsmath.domain.usecase.MenuScreenUseCase
import uz.gita.kidsmath.domain.usecase.impl.GameScreenUseCaseImpl
import uz.gita.kidsmath.domain.usecase.impl.LevelScreenUseCaseImpl
import uz.gita.kidsmath.domain.usecase.impl.MenuScreenUseCaseImpl


@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {


    @Binds
    fun bindLevelUseCase(impl: LevelScreenUseCaseImpl): LevelScreenUseCase

    @Binds
    fun bindMenuUseCase(impl: MenuScreenUseCaseImpl): MenuScreenUseCase

    @Binds
    fun bindGameUseCase(impl: GameScreenUseCaseImpl): GameScreenUseCase
}