package id.agis.core.domain.model

import com.google.gson.annotations.SerializedName

data class DetailRecipe(
    val title: String,

    val thumb: String?,

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