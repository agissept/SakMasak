package id.agis.sakmasak.ui.detail

import androidx.lifecycle.*
import id.agis.core.data.source.Resource
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.RecipeItem
import id.agis.core.domain.usecase.RecipeUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: RecipeUseCase) : ViewModel() {
    private val _recipe = MutableLiveData<RecipeItem?>()
    val recipe: LiveData<RecipeItem?> get() = _recipe

    fun getGetDetailRecipe(recipeKey: String): LiveData<Resource<DetailRecipe>> {
        return useCase.getDetailRecipe(recipeKey).asLiveData()
    }

    fun addRecipeToFavorite(recipe: RecipeItem) {
        viewModelScope.launch {
            useCase.addRecipeToFavorite(recipe)
        }
    }

    fun getFavoriteRecipeById(recipeKey: String) {
        viewModelScope.launch {
            _recipe.postValue(useCase.getFavoriteRecipeById(recipeKey))
        }
    }

    fun removeRecipeFromFavorite(recipe: RecipeItem) {
        viewModelScope.launch {
            useCase.removeRecipeFromFavorite(recipe)
        }
    }
}