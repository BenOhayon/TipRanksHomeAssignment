package com.benohayon.tiprankshomeassignment.model.models

data class NewsItem(
    val id: Long,
    val title: String?,
    val slug: String?,
    val date: String,
    val description: String?,
    val link: String?,
    val seo: Seo?,
    val image: Image?,
    val thumbnail: Thumbnail?,
    val author: Author?,
    val sticky: Boolean,
    val category: Category?,
    val topics: List<Topic>?,
    val stocks: List<Stock>?
)