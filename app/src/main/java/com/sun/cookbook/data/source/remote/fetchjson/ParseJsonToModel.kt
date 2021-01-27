package com.sun.cookbook.data.source.remote.fetchjson

import com.sun.cookbook.data.model.RecipeSlide
import com.sun.cookbook.data.model.RecipeSlideEntry
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
}
