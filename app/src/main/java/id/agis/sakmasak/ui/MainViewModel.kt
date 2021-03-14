package id.agis.sakmasak.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.agis.core.domain.usecase.RecipeUseCase

class MainViewModel(useCase: RecipeUseCase) : ViewModel() {
    val listRecipe = useCase.getListRecipe().asLiveData()
}