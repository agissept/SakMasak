package id.agis.core.domain.model

import id.agis.core.data.source.remote.response.RecipeItemResponse

data class RecipeItem(
    val title: String,
    val thumb: String,
    val key: String,
    val times: String,
    val portion: String,
    val difficulty: String
)

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