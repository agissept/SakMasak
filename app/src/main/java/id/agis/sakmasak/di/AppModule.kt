package id.agis.sakmasak.di

import id.agis.core.domain.usecase.RecipeInteractor
import id.agis.core.domain.usecase.RecipeUseCase
import id.agis.sakmasak.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RecipeUseCase> { RecipeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}