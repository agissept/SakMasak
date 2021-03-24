package id.agis.core.domain.usecase

import id.agis.core.domain.model.RecipeItem
import id.agis.core.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeInteractor(private val recipeRepository: IRecipeRepository) : RecipeUseCase {

    override fun getListRecipe() = recipeRepository.getListRecipe()

    override fun getDetailRecipe(recipeKey: String) =
        recipeRepository.getDetailRecipe(recipeKey)

    override fun getAllFavoriteRecipe(): Flow<List<RecipeItem>> {
        return recipeRepository.getAllFavoriteRecipe()
    }

    override fun getFavoriteRecipeById(recipeKey: String): RecipeItem? {
        return recipeRepository.getFavoriteRecipeById(recipeKey)
    }

    override fun addRecipeToFavorite(recipe: RecipeItem) {
        recipeRepository.addRecipeToFavorite(recipe)
    }

    override fun removeRecipeFromFavorite(recipe: RecipeItem) {
        recipeRepository.removeRecipeFromFavorite(recipe)
    }
}