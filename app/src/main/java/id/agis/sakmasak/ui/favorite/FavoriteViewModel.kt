package id.agis.sakmasak.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.agis.core.domain.usecase.RecipeUseCase

class FavoriteViewModel(useCase: RecipeUseCase) : ViewModel() {
    val listFavoriteRecipe = useCase.getAllFavoriteRecipe().asLiveData()
}