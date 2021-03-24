package id.agis.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "key")
    val key: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "thumb")
    val thumb: String,

    @ColumnInfo(name = "times")
    val times: String,

    @ColumnInfo(name = "portion")
    val portion: String,

    @ColumnInfo(name = "difficulty")
    val difficulty: String
)



