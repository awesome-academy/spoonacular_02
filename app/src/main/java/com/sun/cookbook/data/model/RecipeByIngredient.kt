package com.sun.cookbook.data.model

data class RecipeByIngredient(
    val id: Int?,
    val title: String?,
    val image: String?
)

object RecipeByIngredientEntry {
    const val ID = "id"
    const val TITLE = "title"
    const val IMAGE = "image"
}
