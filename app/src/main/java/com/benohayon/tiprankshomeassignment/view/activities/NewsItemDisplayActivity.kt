package com.benohayon.tiprankshomeassignment.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.benohayon.tiprankshomeassignment.DISPLAY_URL
import com.benohayon.tiprankshomeassignment.R

class NewsItemDisplayActivity : AppCompatActivity() {

    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_item_display)

        initUI()

        intent.extras?.let {
            val displayUrl = it.getString(DISPLAY_URL)
            webView?.loadUrl(displayUrl ?: "")
        }
    }

    private fun initUI() {
        webView = findViewById(R.id.newsItemActivityWebView)
        webView?.webViewClient = WebViewClient()
        webView?.settings?.javaScriptEnabled = true
    }
}