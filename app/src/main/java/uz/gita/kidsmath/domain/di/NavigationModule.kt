package uz.gita.kidsmath.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.kidsmath.presentation.navigator.NavigationDispatcher
import uz.gita.kidsmath.presentation.navigator.NavigationHandler
import uz.gita.kidsmath.presentation.navigator.Navigator

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun navigator(dispatcher: NavigationDispatcher): Navigator

    @Binds
    fun navigatorHandler(dispatcher: NavigationDispatcher): NavigationHandler
}