package com.sun.cookbook.data.source.remote

import com.sun.cookbook.data.source.RecipeDataSource
import com.sun.cookbook.data.source.remote.fetchjson.GetJsonFromUrl
import com.sun.cookbook.utils.Constant
import com.sun.cookbook.utils.TypeModel

class RecipeRemoteDataSource : RecipeDataSource.Remote {

    override fun <T> getDataRecipeSlide(listener: OnFetchDataJsonListener<T>) {
        val stringUrl = Constant.BASE_URL +
                Constant.RANDOM_RECIPE +
                Constant.NUMBER_RECIPE +
                Constant.API_KEY +
                Constant.API_VALUE
        GetJsonFromUrl(TypeModel.RECIPE_SLIDE, listener).execute(stringUrl)
    }

    override fun <T> getDataRecipe(listener: OnFetchDataJsonListener<T>) {
        val stringUrl = Constant.BASE_URL +
                Constant.RANDOM_RECIPE +
                Constant.NUMBER_RECIPE_TEN +
                Constant.API_KEY +
                Constant.API_VALUE
        GetJsonFromUrl(TypeModel.RECIPE, listener).execute(stringUrl)
    }

    private object Holder {
        val INSTANCE = RecipeRemoteDataSource()
    }

    companion object {
        val instance: RecipeRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
