package com.sun.cookbook.data.model

data class Recipe(
    val id: Int,
    val title: String,
    val timeCook: String,
    val score: Double,
    val image: String
)

object RecipeEntry {
    const val LIST_RECIPE = "recipes"
    const val ID = "id"
    const val TITLE = "title"
    const val TIME_COOK = "readyInMinutes"
    const val SCORE = "spoonacularScore"
    const val IMAGE = "image"
}
