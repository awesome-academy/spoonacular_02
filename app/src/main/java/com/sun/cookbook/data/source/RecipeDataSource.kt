package com.sun.cookbook.data.source

import com.sun.cookbook.data.source.remote.OnFetchDataJsonListener

interface RecipeDataSource {
    interface Local
    interface Remote {
        fun <T> getDataRecipeSlide(listener: OnFetchDataJsonListener<T>)
        fun <T> getDataRecipe(listener: OnFetchDataJsonListener<T>)
        fun <T> getDataRecipeDetail(idRecipe: Int, listener: OnFetchDataJsonListener<T>)
        fun <T> getDataRecipeSimilar(idRecipeSimilar: Int, listener: OnFetchDataJsonListener<T>)
        fun <T> getDataRecipeByIngredient(nameIngredient: String, listener: OnFetchDataJsonListener<T>)
        fun <T> searchRecipeByIngredient(ingredients: String, listener: OnFetchDataJsonListener<T>)
        fun <T> searchRecipeByName(name: String, listener: OnFetchDataJsonListener<T>)
    }
}
