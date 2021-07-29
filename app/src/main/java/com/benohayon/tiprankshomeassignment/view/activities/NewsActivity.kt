package com.benohayon.tiprankshomeassignment.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.benohayon.tiprankshomeassignment.R
import com.benohayon.tiprankshomeassignment.model.models.NewsItem
import com.benohayon.tiprankshomeassignment.model.network.ApiNewsItemsRequestBuilder
import com.benohayon.tiprankshomeassignment.view.custom_views.SearchBoxView
import com.benohayon.tiprankshomeassignment.view_model.NewsViewModel

const val TAG = "TEST"

class NewsActivity : AppCompatActivity() {

    private var searchBox: SearchBoxView? = null
    private var newsPostsRecyclerView: RecyclerView? = null
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        initUI()

        viewModel.newsItemsLiveData.observe(this) {
            loadNewsItems(it)
        }
    }

    private fun initUI() {
        searchBox = findViewById(R.id.newsActivitySearchBox)

        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun initRecyclerView() {
        newsPostsRecyclerView = findViewById(R.id.newsActivityNewsRecyclerView)

    }

    private fun loadNewsItems(newsItems: List<NewsItem>) {
        // show loader
        // set the new list in the adapter
    }
}