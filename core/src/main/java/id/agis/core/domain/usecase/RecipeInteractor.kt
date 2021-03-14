package id.agis.core.domain.usecase

import id.agis.core.domain.repository.IRecipeRepository

class RecipeInteractor(private val recipeRepository: IRecipeRepository) : RecipeUseCase {

    override fun getListRecipe() = recipeRepository.getListRecipe()

    override fun getDetailRecipe(recipeKey: String) =
        recipeRepository.getDetailRecipe(recipeKey)
}