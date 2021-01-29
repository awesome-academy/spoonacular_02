package com.sun.cookbook.data.source

import com.sun.cookbook.data.model.Recipe
import com.sun.cookbook.data.model.RecipeSlide
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
    fun getRecipeRecyclerView(listener: OnFetchDataJsonListener<MutableList<Recipe>>) {
        remote.getDataRecipeRecyclerView(listener)
    }

    companion object {
        private var INSTANCE: RecipeRepository? = null
        fun getInstance(): RecipeRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RecipeRepository(
                    remote = RecipeRemoteDataSource.instance,
                    local = RecipeLocalDataSource.instance
                ).also { INSTANCE = it }
            }
        }
    }
}
