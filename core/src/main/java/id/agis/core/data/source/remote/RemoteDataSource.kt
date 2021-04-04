package id.agis.core.data.source.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.agis.core.data.source.remote.network.ApiResponse
import id.agis.core.data.source.remote.network.ApiService
import id.agis.core.data.source.remote.response.detailrecipe.DetailRecipeResponse
import id.agis.core.data.source.remote.response.RecipeItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

class RemoteDataSource(val apiService: ApiService) {

    suspend fun getTodayPicks(): Flow<ApiResponse<List<RecipeItemResponse>>> {
        return flow {
            try {
                val response = apiService.getTodayPicks().results
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailRecipe(recipeKey: String): Flow<ApiResponse<DetailRecipeResponse>> {
        return flow {
            try {
                val response = apiService.getDetailRecipe(recipeKey).results
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    inner class RecipePagingSource : PagingSource<Int, RecipeItemResponse>() {
        private lateinit var listRecipe: List<RecipeItemResponse>
        private var currentPage = 1

        override fun getRefreshKey(state: PagingState<Int, RecipeItemResponse>): Int? = null

        override val keyReuseSupported = true

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeItemResponse> {
            params.key?.let { currentPage = it }

            return try {
                setListRecipes()

                LoadResult.Page(
                    data = listRecipe,
                    prevKey = getPrevPage(),
                    nextKey = getNextPage()
                )
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            } catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }
        }

        private suspend fun setListRecipes() {
            listRecipe = apiService.getListRecipe(currentPage).results
        }

        private fun getNextPage() = if (listRecipe.isEmpty()) null else currentPage + 1

        private fun getPrevPage() = if (currentPage == 1) null else currentPage - 1
    }
}