package com.sun.cookbook.data.model

data class RecipeDetail(
    val title: String?,
    val timeCook: Double?,
    val image: String?,
    val ingredient: ArrayList<Ingredient>?,
    val nutrient: ArrayList<Nutrient>?,
    val step: MutableList<Step>?
)

object RecipeDetailEntry {
    const val INGREDIENT_LIST = "extendedIngredients"
    const val NUTRITION = "nutrition"
    const val NUTRITION_LIST = "nutrients"
    const val ANALYZED_INSTRUCTIONS_LIST = "analyzedInstructions"
    const val STEP_LISTS = "steps"
    const val TITLE = "title"
    const val TIME_COOK = "readyInMinutes"
    const val IMAGE = "image"
}
