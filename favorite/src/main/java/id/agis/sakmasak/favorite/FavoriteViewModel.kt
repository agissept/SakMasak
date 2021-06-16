package id.agis.sakmasak.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.agis.core.domain.usecase.RecipeUseCase

class FavoriteViewModel (useCase: RecipeUseCase) : ViewModel() {
    val lisFavoriteRecipe = useCase.getAllFavoriteRecipe().asLiveData()
}