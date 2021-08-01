package com.benohayon.tiprankshomeassignment.model

import com.benohayon.tiprankshomeassignment.model.models.NewsItem
import com.benohayon.tiprankshomeassignment.model.network.RequestManager

class TipRanksRepository {

    fun getNewsItems(
        pageNumber: Int,
        itemsPerPage: Int,
        searchQuery: String,
        onSuccess: (List<NewsItem>?) -> Unit,
        onFailure: (String?) -> Unit
    ) {
        RequestManager().getNewsItems(
            pageNumber,
            itemsPerPage,
            searchQuery,
            onSuccess,
            onFailure
        )
    }


}