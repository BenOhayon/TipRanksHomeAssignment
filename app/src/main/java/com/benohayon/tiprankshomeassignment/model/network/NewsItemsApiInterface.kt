package com.benohayon.tiprankshomeassignment.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsItemsApiInterface {

    @GET("news/posts")
    fun getNews(
        @Query("page") pageNumber: Int,
        @Query("per_page") perPage: Int,
        @Query("search") searchQuery: String
    ) : Call<Any?>
}