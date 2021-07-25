package com.benohayon.tiprankshomeassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.benohayon.tiprankshomeassignment.view.custom_views.SearchBoxView

class NewsActivity : AppCompatActivity() {

    private var searchBox: SearchBoxView? = null
    private var newsPostsRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        initUI()
    }

    private fun initUI() {
        searchBox = findViewById(R.id.newsActivitySearchBox)

    }
}