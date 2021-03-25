package id.agis.sakmasak.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import id.agis.core.domain.usecase.RecipeUseCase

class HomeViewModel(useCase: RecipeUseCase) : ViewModel() {
    val listRecipe = useCase.getListRecipe().cachedIn(viewModelScope)
}