package com.sun.cookbook.data.source.remote.fetchjson

import com.sun.cookbook.data.model.*
import com.sun.cookbook.utils.TypeModel
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

    fun parseJsonToRecipe(jsonObject: JSONObject?): Recipe? =
        jsonObject?.run {
            Recipe(
                getInt(RecipeEntry.ID),
                getString(RecipeEntry.TITLE),
                getString(RecipeEntry.TIME_COOK),
                getDouble(RecipeEntry.SCORE),
                getString(RecipeEntry.IMAGE)
            )
        }

    fun parseJsonToRecipeDetail(jsonObject: JSONObject?): RecipeDetail? =
        jsonObject?.run {
            try {
                val parseDataWithJson = ParseDataWithJson()
                val ingredient = parseDataWithJson.parseJsonToArray(
                    getJSONArray(RecipeDetailEntry.INGREDIENT_LIST),
                    TypeModel.INGREDIENT
                ) as MutableList<Ingredient>

                val nutrient = parseDataWithJson.parseJsonToArray(
                    getJSONObject(RecipeDetailEntry.NUTRITION).getJSONArray(RecipeDetailEntry.NUTRITION_LIST),
                    TypeModel.NUTRIENT
                ) as MutableList<Nutrient>

                val step = parseDataWithJson.parseJsonToArray(
                    getJSONArray(RecipeDetailEntry.ANALYZED_INSTRUCTIONS_LIST).getJSONObject(0)
                        .getJSONArray(RecipeDetailEntry.STEP_LISTS), TypeModel.STEP
                ) as MutableList<Step>

                RecipeDetail(
                    getString(RecipeDetailEntry.TITLE),
                    getDouble(RecipeDetailEntry.TIME_COOK),
                    getString(RecipeDetailEntry.IMAGE),
                    ingredient,
                    nutrient,
                    step
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    fun parseJsonToIngredient(jsonObject: JSONObject?): Ingredient? =
        jsonObject?.run {
            Ingredient(
                getInt(IngredientEntry.ID),
                getString(IngredientEntry.NAME),
                getString(IngredientEntry.IMAGE),
                getDouble(IngredientEntry.AMOUNT),
                getString(IngredientEntry.UNIT)
            )
        }

    fun parseJsonToNutrient(jsonObject: JSONObject?): Nutrient? =
        jsonObject?.run {
            Nutrient(
                getString(NutrientEntry.NAME),
                getDouble(NutrientEntry.AMOUNT),
                getString(NutrientEntry.UNIT)
            )
        }

    fun parseJsonToStep(jsonObject: JSONObject?): Step? =
        jsonObject?.run {
            Step(
                getInt(StepEntry.NUMBER),
                getString(StepEntry.STEP)
            )
        }
    fun parseJsonToRecipeSimilar(jsonObject: JSONObject?): RecipeSimilar? =
        jsonObject?.run {
            RecipeSimilar(
                getInt(RecipeSimilarEntry.ID),
                getString(RecipeSimilarEntry.TITLE),
                getString(RecipeSimilarEntry.TIME_COOK)
            )
        }
}
