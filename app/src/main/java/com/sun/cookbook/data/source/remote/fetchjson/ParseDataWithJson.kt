package com.sun.cookbook.data.source.remote.fetchjson

import com.sun.cookbook.data.model.RecipeSlideEntry
import com.sun.cookbook.utils.Constant
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

    fun parseJsonToDaTa(jsonObject: JSONObject?, keyEntity: String): Any {
        val data = mutableListOf<Any>()
        try {
            val jsonArray = jsonObject?.getJSONArray(RecipeSlideEntry.LIST_RECIPE)
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val jsonObjects = jsonArray?.getJSONObject(i)
                val item = parseJsonToObject(jsonObjects, keyEntity)
                item?.let { data.add(item) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    private fun parseJsonToObject(jsonObject: JSONObject?, typeModel: String): Any? {
        try {
            jsonObject?.let {
                when (typeModel) {
                    Constant.RECIPE_SLIDE -> return ParseJsonToModel().parseJsonToRecipeSlide(it)
                    else -> null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        private const val TIME_OUT = 20000
        private const val METHOD_GET = "GET"
    }
}