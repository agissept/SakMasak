package id.agis.core.domain.usecase

import androidx.paging.PagingData
import id.agis.core.data.source.Resource
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow

interface RecipeUseCase {
    fun geTodayPicks(): Flow<Resource<List<RecipeItem>>>

    fun getListRecipe(): Flow<PagingData<RecipeItem>>

    fun getDetailRecipe(recipeKey: String): Flow<Resource<DetailRecipe>>

    fun getAllFavoriteRecipe(): Flow<List<RecipeItem>>

    suspend fun addRecipeToFavorite(recipe: RecipeItem)

    suspend fun removeRecipeFromFavorite(recipe: RecipeItem)

    suspend fun getFavoriteRecipeById(recipeKey: String): RecipeItem?
}