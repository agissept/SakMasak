package id.agis.sakmasak.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.agis.core.data.source.Resource
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.usecase.RecipeUseCase

class DetailViewModel(private val useCase: RecipeUseCase) : ViewModel() {
    fun getGetDetailRecipe(recipeKey: String): LiveData<Resource<DetailRecipe>> {
        return useCase.getDetailRecipe(recipeKey).asLiveData()
    }
}