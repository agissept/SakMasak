package id.agis.core.data.source.local.room

import androidx.room.*
import id.agis.core.data.source.local.entity.RecipeEntity
import id.agis.core.domain.model.RecipeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAllFavoriteRecipe(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe WHERE `key` =:recipeKey")
    suspend fun getFavoriteRecipeById(recipeKey: String): RecipeItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipeToFavorite(recipe: RecipeEntity)

    @Delete
    suspend fun removeRecipeFromFavorite(recipe: RecipeEntity)
}