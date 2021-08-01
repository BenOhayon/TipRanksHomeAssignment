package com.benohayon.tiprankshomeassignment.view.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benohayon.tiprankshomeassignment.DISPLAY_URL
import com.benohayon.tiprankshomeassignment.PAGINATION_PAGE_SIZE
import com.benohayon.tiprankshomeassignment.PROMOTION_DIRECT_URL
import com.benohayon.tiprankshomeassignment.R
import com.benohayon.tiprankshomeassignment.model.NewsItemsListScrollListener
import com.benohayon.tiprankshomeassignment.model.adapters.NewsItemsAdapter
import com.benohayon.tiprankshomeassignment.model.models.NewsItem
import com.benohayon.tiprankshomeassignment.view.custom_views.SearchBoxView
import com.benohayon.tiprankshomeassignment.view_model.NewsViewModel

class NewsActivity : AppCompatActivity() {

    private var searchBox: SearchBoxView? = null
    private var newsPostsRecyclerView: RecyclerView? = null
    private var progressBar: View? = null

    private lateinit var newsItemsAdapter: NewsItemsAdapter
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var scrollListener: NewsItemsListScrollListener

    private var newsItems: MutableList<NewsItem> = mutableListOf()
    private var afterSearch = false
    private var currentPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        initUI()

        viewModel.newsItemsLiveData.observe(this) {
            scrollListener.notifyDataLoaded()

            if (it.size < PAGINATION_PAGE_SIZE) {
                scrollListener.setHaveMoreData(false)
                currentPage = 1
            }

            if (it.isNotEmpty()) {
                if (afterSearch)
                    setList(it)
                else
                    newsItems.addAll(it)

                progressBar?.visibility = View.INVISIBLE
                newsItemsAdapter.updateList(newsItems)
            }
        }
    }

    private fun initUI() {
        searchBox = findViewById(R.id.newsActivitySearchBox)
        progressBar = findViewById(R.id.newsActivityProgressBar)

        searchBox?.setOnSearchListener(object: SearchBoxView.SearchBoxViewListener {
            override fun onSearchClick(searchQuery: String) {
                afterSearch = true
                loadNewsItems(searchQuery, currentPage)
            }

            override fun onTextChanged() {
                currentPage = 1
            }

        })

        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        newsItems = mutableListOf()
        loadNewsItems(searchBox?.searchQuery ?: "", currentPage)
    }

    private fun initRecyclerView() {
        newsPostsRecyclerView = findViewById(R.id.newsActivityNewsRecyclerView)
        newsItemsAdapter = NewsItemsAdapter(object: NewsItemsAdapter.NewsItemsAdapterListener {
            override fun onItemClick(link: String?) {
                link?.let {
                    openWebDisplay(it)
                }
            }

            override fun onPromote() {
                openWebBrowser(PROMOTION_DIRECT_URL)
            }
        })

        newsPostsRecyclerView?.apply {
            val layoutManager = LinearLayoutManager(this@NewsActivity)
            this.layoutManager = layoutManager
            this.adapter = newsItemsAdapter
            scrollListener = object: NewsItemsListScrollListener(layoutManager) {
                override fun requestData(pageNum: Int) {
                    currentPage = pageNum
                    loadNewsItems(searchBox?.searchQuery ?: "", currentPage)
                }
            }
            addOnScrollListener(scrollListener)
        }
    }

    private fun openWebBrowser(link: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

    private fun openWebDisplay(link: String) {
        val toWebDisplay = Intent(this, NewsItemDisplayActivity::class.java)
            .putExtra(DISPLAY_URL, link)
        startActivity(toWebDisplay)
    }

    private fun loadNewsItems(searchQuery: String, page: Int) {
        progressBar?.visibility = View.VISIBLE
        viewModel.loadNewsItems(searchQuery, page)
    }

    private fun setList(newsItems: List<NewsItem>) {
        this.newsItems.clear()
        this.newsItems.addAll(newsItems)
    }
}