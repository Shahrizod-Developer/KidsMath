package uz.gita.kidsmath.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.kidsmath.data.repository.AppRepository
import uz.gita.kidsmath.domain.usecase.MenuScreenUseCase
import javax.inject.Inject

class MenuScreenUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : MenuScreenUseCase {
    override fun getLevel(): Flow<String> = repository.getLevel()

    override suspend fun setLevel(level: String) = repository.setLevel(level)
    override suspend fun generate() {
        repository.generateQuestion()
    }
}