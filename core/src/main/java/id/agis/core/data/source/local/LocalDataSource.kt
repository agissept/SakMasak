package id.agis.core.data.source.local

import id.agis.core.data.source.local.entity.RecipeEntity
import id.agis.core.data.source.local.room.RecipeDao

class LocalDataSource(private val recipeDao: RecipeDao) {

    fun getAllFavoriteRecipe() =
        recipeDao.getAllFavoriteRecipe()

    suspend fun getFavoriteRecipeById(recipeKey: String) =
        recipeDao.getFavoriteRecipeById(recipeKey)

    suspend fun addRecipeToFavorite(recipe: RecipeEntity) =
        recipeDao.addRecipeToFavorite(recipe)

    suspend fun removeRecipeFromFavorite(recipe: RecipeEntity) =
        recipeDao.removeRecipeFromFavorite(recipe)
}