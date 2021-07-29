package com.benohayon.tiprankshomeassignment.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benohayon.tiprankshomeassignment.model.TipRanksRepository
import com.benohayon.tiprankshomeassignment.model.models.NewsItem

class NewsViewModel : ViewModel() {

    private val repository = TipRanksRepository()
    val newsItemsLiveData: MutableLiveData<List<NewsItem>> by lazy {
        MutableLiveData<List<NewsItem>>()
    }

    fun getNewsItems(
        pageNumber: Int,
        itemsPerPage: Int,
        searchQuery: String
    ) {
        repository.getNewsItems(pageNumber, itemsPerPage, searchQuery, { newsItems ->
            newsItemsLiveData.postValue(newsItems)
        }, {
            newsItemsLiveData.postValue(listOf())
        })
    }
}