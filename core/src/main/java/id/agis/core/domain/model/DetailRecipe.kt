package id.agis.core.domain.model

import com.google.gson.annotations.SerializedName
import id.agis.core.data.source.remote.response.detailrecipe.DetailRecipeResponse

data class DetailRecipe(
    val title: String,

    val thumb: String,

    val servings: String,

    val times: String,

    val difficulty: String,

    val author: Author,

    val desc: String,

    val ingredient: List<String>,

    val step: List<String>,
)

data class Author(
    @field:SerializedName("user")
    val user: String,

    @field:SerializedName("datePublished")
    val datePublished: String
)


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