package id.agis.core.domain.usecase

import id.agis.core.data.source.Resource
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow

interface RecipeUseCase {
    fun getListRecipe(): Flow<Resource<List<RecipeItem>>>

    fun getDetailRecipe(recipeKey: String): Flow<Resource<DetailRecipe>>
}