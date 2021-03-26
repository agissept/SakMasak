package id.agis.core.domain.repository

import androidx.paging.PagingData
import id.agis.core.data.source.Resource
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {
    fun getListRecipe(): Flow<PagingData<RecipeItem>>

    fun getDetailRecipe(recipeKey: String): Flow<Resource<DetailRecipe>>

    fun getAllFavoriteRecipe(): Flow<List<RecipeItem>>

    suspend fun getFavoriteRecipeById(recipeKey: String): RecipeItem?

    suspend fun addRecipeToFavorite(recipe: RecipeItem)

    suspend fun removeRecipeFromFavorite(recipe: RecipeItem)
}