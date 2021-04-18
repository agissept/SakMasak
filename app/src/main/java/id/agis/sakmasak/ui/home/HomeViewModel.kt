package id.agis.sakmasak.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import id.agis.core.domain.usecase.RecipeUseCase

class HomeViewModel(useCase: RecipeUseCase) : ViewModel() {
    val todayPicks = useCase.geTodayPicks().asLiveData()

    val listRecipe = useCase.getListRecipe().cachedIn(viewModelScope)

    val lisFavoriteRecipe = useCase.getAllFavoriteRecipe().asLiveData()
}