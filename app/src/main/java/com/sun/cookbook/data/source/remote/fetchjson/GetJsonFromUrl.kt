package com.sun.cookbook.data.source.remote.fetchjson

import android.os.AsyncTask
import com.sun.cookbook.data.source.remote.OnFetchDataJsonListener
import org.json.JSONObject

class GetJsonFromUrl<T> constructor(
    private val typeMode: String,
    private val jsonListener: OnFetchDataJsonListener<T>
) : AsyncTask<String?, Unit?, String>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: String?): String {
        var data = ""
        try {
            params[0]?.let { data = ParseDataWithJson().getJsonFromUrl(it) }
        } catch (e: Exception) {
            exception = e
        }
        return data
    }

    @Suppress("UNCHECKED_CAST")
    override fun onPostExecute(result: String) {
        if (result.isEmpty()) jsonListener.onError(exception)
        else {
            val jsonObject = JSONObject(result)
            val data = ParseDataWithJson().parseJsonToDaTa(jsonObject, typeMode)
            data.let {
                jsonListener.onSuccess(it as T)
            }
        }
    }
}
