package id.agis.core.data.source.remote

import android.util.Log
import id.agis.core.data.source.remote.network.ApiResponse
import id.agis.core.data.source.remote.network.ApiService
import id.agis.core.data.source.remote.response.detailrecipe.DetailRecipeResponse
import id.agis.core.data.source.remote.response.RecipeItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getListRecipe(): Flow<ApiResponse<List<RecipeItemResponse>>> {
        return flow {
            try {
                val response = apiService.getListRecipe()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getDetailRecipe(recipeKey: String): Flow<ApiResponse<DetailRecipeResponse>> {
        return flow {
            try {
                val response = apiService.getDetailRecipe(recipeKey).result
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}