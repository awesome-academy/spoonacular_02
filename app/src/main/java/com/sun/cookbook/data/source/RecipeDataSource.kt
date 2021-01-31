package com.sun.cookbook.data.source

import com.sun.cookbook.data.source.remote.OnFetchDataJsonListener

interface RecipeDataSource {
    interface Local
    interface Remote {
        fun <T> getDataRecipeSlide(listener: OnFetchDataJsonListener<T>)
        fun <T> getDataRecipe(listener: OnFetchDataJsonListener<T>)
    }
}
