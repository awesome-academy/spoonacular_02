package com.sun.cookbook.data.source

import com.sun.cookbook.data.model.*
import com.sun.cookbook.data.source.local.RecipeLocalDataSource
import com.sun.cookbook.data.source.remote.OnFetchDataJsonListener
import com.sun.cookbook.data.source.remote.RecipeRemoteDataSource

class RecipeRepository private constructor(
    private val remote: RecipeDataSource.Remote,
    private val local: RecipeDataSource.Local
) {
    fun getRecipeSlide(listener: OnFetchDataJsonListener<MutableList<RecipeSlide>>) {
        remote.getDataRecipeSlide(listener)
    }

    fun getRecipe(listener: OnFetchDataJsonListener<MutableList<Recipe>>) {
        remote.getDataRecipe(listener)
    }

    fun getRecipeDetail(idRecipe: Int, listener: OnFetchDataJsonListener<RecipeDetail>) {
        remote.getDataRecipeDetail(idRecipe, listener)
    }

    fun getRecipeSimilar(
        idRecipe: Int,
        listener: OnFetchDataJsonListener<MutableList<RecipeSimilar>>
    ) {
        remote.getDataRecipeSimilar(idRecipe, listener)
    }

    fun getRecipeByIngredient(
        nameIngredient: String,
        listener: OnFetchDataJsonListener<MutableList<RecipeByIngredient>>
    ) {
        remote.getDataRecipeByIngredient(nameIngredient, listener)
    }

    fun searchRecipeByIngredient(
        ingredients: String, listener: OnFetchDataJsonListener<MutableList<RecipeByIngredient>>
    ) {
        remote.searchRecipeByIngredient(ingredients, listener)
    }

    fun searchRecipeByName(
        name: String,
        listener: OnFetchDataJsonListener<MutableList<RecipeByIngredient>>
    ) {
        remote.searchRecipeByName(name, listener)
    }

    companion object {
        private var INSTANCE: RecipeRepository? = null
        fun getInstance(
            remote: RecipeDataSource.Remote,
            local: RecipeDataSource.Local
        ): RecipeRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RecipeRepository(remote, local).also { INSTANCE = it }
            }
        }
    }
}
