package com.benohayon.tiprankshomeassignment.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ApiRequestBuilder {

    abstract val baseUrl: String
    val requestBuilder: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}