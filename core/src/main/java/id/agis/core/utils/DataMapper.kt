package id.agis.core.utils

import id.agis.core.data.source.remote.response.RecipeItemResponse
import id.agis.core.data.source.remote.response.detailrecipe.DetailRecipeResponse
import id.agis.core.domain.model.Author
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.RecipeItem

fun DetailRecipeResponse.toDomainModel(): DetailRecipe {
    return DetailRecipe(
        title = title,
        thumb = thumb,
        servings = servings,
        times = times,
        difficulty = difficulty,
        author = Author(author.user, author.datePublished),
        desc = desc,
        ingredient = ingredient,
        step = step,
    )
}

fun RecipeItemResponse.toDomainModel(): RecipeItem {
    return RecipeItem(
        title = title,
        thumb = thumb,
        key = key,
        times = times,
        portion = portion,
        difficulty = difficulty
    )
}