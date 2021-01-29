package com.sun.cookbook.data.source.remote.fetchjson

import com.sun.cookbook.data.model.*
import org.json.JSONObject

class ParseJsonToModel {

    fun parseJsonToRecipeSlide(jsonObject: JSONObject?): RecipeSlide? =
        jsonObject?.run {
            RecipeSlide(
                getInt(RecipeSlideEntry.ID),
                getString(RecipeSlideEntry.TITLE),
                getString(RecipeSlideEntry.TIME_COOK),
                getString(RecipeSlideEntry.IMAGE)
            )
        }

    fun parseJsonToRecipeRecyclerView(jsonObject: JSONObject?): Recipe? =
        jsonObject?.run {
            Recipe(
                getInt(RecipeEntry.ID),
                getString(RecipeEntry.TITLE),
                getString(RecipeEntry.TIME_COOK),
                getDouble(RecipeEntry.SCORE),
                getString(RecipeEntry.IMAGE)
            )
        }
}
