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

    override fun <T> getDataRecipeDetail(idRecipe: Int, listener: OnFetchDataJsonListener<T>) {
        val stringUrl = Constant.BASE_URL +
                idRecipe +
                Constant.RECIPE_INFORMATION +
                Constant.API_KEY_DETAIL +
                Constant.API_VALUE +
                Constant.NUTRIENT_TRUE
        GetJsonFromUrl(TypeModel.RECIPE_DETAIL, listener).execute(stringUrl)
    }

    override fun <T> getDataRecipeSimilar(
        idRecipeSimilar: Int,
        listener: OnFetchDataJsonListener<T>
    ) {
        val stringUrl = Constant.BASE_URL +
                idRecipeSimilar +
                Constant.RECIPE_SIMILAR +
                Constant.API_KEY_DETAIL +
                Constant.API_VALUE
        GetJsonFromUrl(TypeModel.RECIPE_SIMILAR, listener).execute(stringUrl)
    }

    override fun <T> getDataRecipeByIngredient(
        nameIngredient: String,
        listener: OnFetchDataJsonListener<T>
    ) {
        val stringUrl = Constant.BASE_URL +
                Constant.BY_INGREDIENT +
                nameIngredient +
                Constant.NUMBER_RECIPE +
                Constant.API_KEY +
                Constant.API_VALUE
        GetJsonFromUrl(TypeModel.DETAIL_INGREDIENT, listener).execute(stringUrl)
    }

    override fun <T> searchRecipeByName(name: String, listener: OnFetchDataJsonListener<T>) {
        val stringUrl =
            Constant.BASE_URL +
                    Constant.BY_NAME +
                    name.trim() +
                    Constant.NUMBER_SEARCH +
                    Constant.API_KEY +
                    Constant.API_VALUE
        GetJsonFromUrl(TypeModel.SEARCH_BY_NAME, listener).execute(stringUrl)
    }

    override fun <T> searchRecipeByIngredient(
        ingredients: String,
        listener: OnFetchDataJsonListener<T>
    ) {
        val stringUrl =
            Constant.BASE_URL +
                    Constant.BY_INGREDIENT +
                    ingredients.replace("\\s".toRegex(), "%2C") +
                    Constant.NUMBER_RECIPE +
                    Constant.API_KEY +
                    Constant.API_VALUE
        GetJsonFromUrl(TypeModel.SEARCH_BY_INGREDIENT, listener).execute(stringUrl)
    }

    private object Holder {
        val INSTANCE = RecipeRemoteDataSource()
    }

    companion object {
        val instance: RecipeRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
