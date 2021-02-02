package com.sun.cookbook.data.model

data class RecipeSimilar(
    val id: Int?,
    val title: String?,
    val timeCook: String?
)

object RecipeSimilarEntry {
    const val ID = "id"
    const val TITLE = "title"
    const val TIME_COOK = "readyInMinutes"
}
