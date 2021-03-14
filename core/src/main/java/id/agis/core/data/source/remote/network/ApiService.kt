package id.agis.core.data.source.remote.network

import id.agis.core.data.source.remote.response.ListRecipeResponse
import id.agis.core.data.source.remote.response.detailrecipe.ObjectDetailRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun getListRecipe(): ListRecipeResponse

    @GET("recipe/{key}")
    suspend fun getDetailRecipe(
        @Path(value = "key", encoded = true) recipeKey: String
    ): ObjectDetailRecipeResponse
}
