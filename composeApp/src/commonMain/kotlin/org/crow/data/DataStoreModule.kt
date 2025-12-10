package org.crow.data

import org.crow.ui.screens.UserScreen
import org.crow.viewModel.AuthScreenViewModel
import org.crow.viewModel.GamesScreenViewModel
import org.crow.viewModel.GameDetailScreenViewModel
import org.crow.viewModel.LoginViewModel
import org.crow.viewModel.ReviewGamesScreenViewModel
import org.crow.viewModel.ReviewScreenViewModel
import org.crow.viewModel.SearchScreenViewModel
import org.crow.viewModel.UserScreenViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val dataStoreModule: Module
val repositoryModule = module {
    single { DataRepository() }

    single { AppPreferencesRepository(get()) }
}

val viewModelModule = module {

    viewModel { AuthScreenViewModel(get(), get()) }
    viewModel { GamesScreenViewModel(get()) }
    viewModel { GameDetailScreenViewModel(get(),get(),get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { UserScreenViewModel(get(),get()) }
    viewModel { ReviewScreenViewModel(get(),get(),get(),get()) }
    viewModel { SearchScreenViewModel(get()) }
    viewModel { ReviewGamesScreenViewModel(get(),get(),get()) }
}

fun getKoinModules(): List<Module> = listOf(
    dataStoreModule,
    repositoryModule,
    viewModelModule,
)