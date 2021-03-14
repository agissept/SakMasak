package id.agis.core.data.source.remote.response.detailrecipe

import com.google.gson.annotations.SerializedName

data class DetailRecipeResponse(
    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("thumb")
    val thumb: String,

    @field:SerializedName("servings")
    val servings: String,

    @field:SerializedName("times")
    val times: String,

    @field:SerializedName("dificulty")
    val difficulty: String,

    @field:SerializedName("author")
    val author: Author,

    @field:SerializedName("desc")
    val desc: String,

    @field:SerializedName("ingredient")
    val ingredient: List<String>,

    @field:SerializedName("step")
    val step: List<String>
)


data class Author(
    @field:SerializedName("user")
    val user: String,

    @field:SerializedName("datePublished")
    val datePublished: String
)