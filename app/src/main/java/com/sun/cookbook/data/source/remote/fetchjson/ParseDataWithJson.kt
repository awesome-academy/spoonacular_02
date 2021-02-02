package com.sun.cookbook.data.source.remote.fetchjson

import android.util.Log
import com.sun.cookbook.data.model.RecipeDetailEntry
import com.sun.cookbook.data.model.RecipeEntry
import com.sun.cookbook.data.model.RecipeSimilarEntry
import com.sun.cookbook.data.model.RecipeSlideEntry
import com.sun.cookbook.utils.TypeModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ParseDataWithJson {

    fun getJsonFromUrl(urlString: String): String {
        val url = URL(urlString)
        val httpURLConnect = (url.openConnection() as HttpURLConnection).apply {
            requestMethod = METHOD_GET
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            doOutput = true
            connect()
        }
        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        bufferedReader.close()
        httpURLConnect.disconnect()
        return stringBuilder.toString()
    }

    fun parseJsonToData(jsonString: String, typeModel: TypeModel): Any? {

        return when (typeModel) {
            TypeModel.RECIPE_SLIDE -> {
                parseJsonToArray(
                    JSONObject(jsonString).getJSONArray(RecipeSlideEntry.LIST_RECIPE), typeModel
                )
            }
            TypeModel.RECIPE -> {
                parseJsonToArray(
                    JSONObject(jsonString).getJSONArray(RecipeEntry.LIST_RECIPE), typeModel
                )
            }
            TypeModel.INGREDIENT -> {
                parseJsonToArray(
                    JSONObject(jsonString).getJSONArray(RecipeDetailEntry.INGREDIENT_LIST),
                    typeModel
                )
            }
            TypeModel.NUTRIENT -> {
                parseJsonToArray(
                    JSONObject(jsonString).getJSONArray(RecipeDetailEntry.NUTRITION_LIST), typeModel
                )
            }
            TypeModel.STEP -> {
                parseJsonToArray(
                    JSONObject(jsonString).getJSONArray(RecipeDetailEntry.STEP_LISTS), typeModel
                )
            }
            TypeModel.RECIPE_DETAIL -> {
                parseJsonToObject(JSONObject(jsonString), typeModel)
            }
            TypeModel.RECIPE_SIMILAR -> {
                parseJsonToArray(
                    JSONArray(jsonString),typeModel
                )
            }
        }
    }

    private fun parseJsonToObject(jsonObject: JSONObject?, typeModel: TypeModel): Any? {
        return try {
            val parser = ParseJsonToModel()
            when (typeModel) {
                TypeModel.RECIPE_SLIDE -> jsonObject?.let(parser::parseJsonToRecipeSlide)
                TypeModel.RECIPE -> jsonObject?.let(parser::parseJsonToRecipe)
                TypeModel.RECIPE_DETAIL -> jsonObject?.let(parser::parseJsonToRecipeDetail)
                TypeModel.INGREDIENT -> jsonObject?.let(parser::parseJsonToIngredient)
                TypeModel.NUTRIENT -> jsonObject?.let(parser::parseJsonToNutrient)
                TypeModel.STEP -> jsonObject?.let(parser::parseJsonToStep)
                TypeModel.RECIPE_SIMILAR -> jsonObject?.let(parser::parseJsonToRecipeSimilar)
            }
        } catch (e: Exception) {
            null
        }
    }

    fun parseJsonToArray(jsonArray: JSONArray?, typeModel: TypeModel): Any {
        val data = mutableListOf<Any?>()
        for (i in 0 until (jsonArray?.length() ?: 0)) {
            val jsonObject = jsonArray?.getJSONObject(i)
            data.add(parseJsonToObject(jsonObject, typeModel))
        }
        return data.filterNotNull()
    }

    companion object {

        private const val TIME_OUT = 20000
        private const val METHOD_GET = "GET"
    }
}
