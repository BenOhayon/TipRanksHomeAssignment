package com.benohayon.tiprankshomeassignment.model.network

import android.util.Log
import com.benohayon.tiprankshomeassignment.model.models.NewsItem
import com.benohayon.tiprankshomeassignment.model.parsers.NewsItemParser
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "TEST"

class ApiNewsItemsRequestBuilder : ApiRequestBuilder() {

    override val baseUrl: String
        get() = "https://tipranks.com/api/"


    fun getNewsItems(
        pageNumber: Int,
        perPage: Int,
        searchQuery: String,
        onSuccess: (List<NewsItem>?) -> Unit = {},
        onFailure: (String?) -> Unit = {}
    ) {
        val newsItemsApiInterface = requestBuilder.create(NewsItemsApiInterface::class.java)
        val getNewsItemsCall = newsItemsApiInterface.getNews(pageNumber, perPage, searchQuery)
        getNewsItemsCall.enqueue(object: Callback<Any?> {
            override fun onResponse(
                call: Call<Any?>,
                response: Response<Any?>
            ) {
                if (response.isSuccessful) {
                    val responseJson = JSONObject(Gson().toJson(response.body()))
                    val newsItems : List<NewsItem>? =
                        if (!responseJson.isNull("data"))
                            NewsItemParser().parseObjectList(responseJson.getJSONArray("data"))
                        else
                            null

                    onSuccess.invoke(newsItems)
                } else
                    onFailure.invoke("Error code: ${response.code()}")
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }

        })
    }
}