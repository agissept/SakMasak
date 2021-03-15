package id.agis.sakmasak.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.agis.core.domain.usecase.RecipeUseCase

class HomeActivity(useCase: RecipeUseCase) : ViewModel() {
    val listRecipe = useCase.getListRecipe().asLiveData()
}