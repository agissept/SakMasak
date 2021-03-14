package id.agis.core.data.source

import id.agis.core.data.source.remote.RemoteDataSource
import id.agis.core.data.source.remote.network.ApiResponse
import id.agis.core.data.source.remote.response.detailrecipe.DetailRecipeResponse
import id.agis.core.data.source.remote.response.RecipeItemResponse
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.RecipeItem
import id.agis.core.utils.toDomainModel
import id.agis.core.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val remoteDataSource: RemoteDataSource) : IRecipeRepository {
    override fun getListRecipe(): Flow<Resource<List<RecipeItem>>> =
        object : NetworkBoundResource<List<RecipeItem>, List<RecipeItemResponse>>() {
            override fun convertDataToDomainModel(data: List<RecipeItemResponse>): List<RecipeItem> {
                return data.map {
                    it.toDomainModel()
                }
            }

            override suspend fun getDataFromNetwork(): Flow<ApiResponse<List<RecipeItemResponse>>> =
                remoteDataSource.getListRecipe()

        }.asFlow()

    override fun getDetailRecipe(recipeKey: String): Flow<Resource<DetailRecipe>> =
        object : NetworkBoundResource<DetailRecipe, DetailRecipeResponse>() {
            override fun convertDataToDomainModel(data: DetailRecipeResponse): DetailRecipe =
                data.toDomainModel()


            override suspend fun getDataFromNetwork(): Flow<ApiResponse<DetailRecipeResponse>> =
                remoteDataSource.getDetailRecipe(recipeKey)


        }.asFlow()
}
