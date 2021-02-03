package com.sun.cookbook.data.model

data class RecipeSlide(val id: Int?, val title: String?, val timeCook: String?, val image: String?)
object RecipeSlideEntry {
    const val LIST_RECIPE = "recipes"
    const val ID = "id"
    const val TITLE = "title"
    const val TIME_COOK = "readyInMinutes"
    const val IMAGE = "image"
}
