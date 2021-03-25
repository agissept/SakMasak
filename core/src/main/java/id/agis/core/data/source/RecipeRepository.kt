package id.agis.core.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import id.agis.core.data.source.remote.RemoteDataSource
import id.agis.core.data.source.remote.network.ApiResponse
import id.agis.core.data.source.remote.response.detailrecipe.DetailRecipeResponse
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.RecipeItem
import id.agis.core.utils.toDomainModel
import id.agis.core.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepository(private val remoteDataSource: RemoteDataSource) : IRecipeRepository {

    override fun getListRecipe(): Flow<PagingData<RecipeItem>> =
        Pager(
            config = PagingConfig(PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { remoteDataSource.RecipePagingSource() }
        ).flow.map {
            it.map { recipeItemResponse ->
                recipeItemResponse.toDomainModel()
            }
        }


    override fun getDetailRecipe(recipeKey: String): Flow<Resource<DetailRecipe>> =
        object : NetworkBoundResource<DetailRecipe, DetailRecipeResponse>() {
            override fun convertDataToDomainModel(data: DetailRecipeResponse): DetailRecipe =
                data.toDomainModel()


            override suspend fun getDataFromNetwork(): Flow<ApiResponse<DetailRecipeResponse>> =
                remoteDataSource.getDetailRecipe(recipeKey)


        }.asFlow()

    companion object {
        private const val PAGE_SIZE = 20
    }
}
