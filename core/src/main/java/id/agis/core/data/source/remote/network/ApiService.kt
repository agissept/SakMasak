package id.agis.core.data.source.remote.network

import id.agis.core.data.source.remote.response.ListRecipeResponse
import id.agis.core.data.source.remote.response.detailrecipe.ObjectDetailRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("recipes/100")
    suspend fun getTodayPicks(): ListRecipeResponse

    @GET("recipes/{page}")
    suspend fun getListRecipe(@Path("page") page: Int): ListRecipeResponse

    @GET("recipe/{key}")
    suspend fun getDetailRecipe(
        @Path(value = "key", encoded = true) recipeKey: String
    ): ObjectDetailRecipeResponse
}
