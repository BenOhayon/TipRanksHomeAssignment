package com.benohayon.tiprankshomeassignment.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benohayon.tiprankshomeassignment.PAGINATION_PAGE_SIZE
import com.benohayon.tiprankshomeassignment.model.TipRanksRepository
import com.benohayon.tiprankshomeassignment.model.models.NewsItem

class NewsViewModel : ViewModel() {

    private val repository = TipRanksRepository()
    val newsItemsLiveData: MutableLiveData<List<NewsItem>> by lazy {
        MutableLiveData<List<NewsItem>>()
    }

    fun loadNewsItems(
        searchQuery: String,
        pageNumber: Int,
        itemsPerPage: Int = PAGINATION_PAGE_SIZE
    ) {
        repository.getNewsItems(pageNumber, itemsPerPage, searchQuery, { newsItems ->
            newsItemsLiveData.postValue(newsItems)
        }, {
            newsItemsLiveData.postValue(listOf())
        })
    }
}