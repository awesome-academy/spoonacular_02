package com.sun.cookbook.utils

import com.sun.cookbook.BuildConfig

object Constant {
    const val BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes"
    const val BASE_URL_IMAGE_RECIPE = "https://spoonacular.com/recipeImages/"
    const val BASE_URL_IMAGE_INGREDIENT = "https://spoonacular.com/cdn/ingredients_100x100/"
    const val NULL_STRING = "null"
    const val NUMBER_RECIPE = "?number=5"
    const val NUMBER_RECIPE_TEN = "?number=10"
    const val RANDOM_RECIPE = "/random"
    const val API_KEY = "&rapidapi-key="
    const val API_VALUE = BuildConfig.API_VALUE
    const val RECIPE_DETAIL = "RECIPE_DETAIL"
    const val RECIPE_ITEM = "RECIPE_ITEM"
    const val INGREDIENT = "INGREDIENT"
    const val NUTRIENT = "NUTRIENT"
}
