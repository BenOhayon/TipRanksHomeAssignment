package com.benohayon.tiprankshomeassignment.model.network

import com.benohayon.tiprankshomeassignment.model.models.NewsItem

class RequestManager {

    fun getNewsItems(
        pageNumber: Int,
        itemsPerPage: Int,
        searchQuery: String,
        onSuccess: (List<NewsItem>?) -> Unit,
        onFailure: (String?) -> Unit
    ) {
        ApiNewsItemsRequestBuilder().getNewsItems(
            pageNumber,
            itemsPerPage,
            searchQuery,
            onSuccess,
            onFailure
        )
    }
}