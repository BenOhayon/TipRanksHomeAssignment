package com.benohayon.tiprankshomeassignment.model.parsers

import com.benohayon.tiprankshomeassignment.*
import com.benohayon.tiprankshomeassignment.model.models.NewsItem
import com.benohayon.tiprankshomeassignment.model.parsers.abs.GeneralParser
import org.json.JSONObject

class NewsItemParser : GeneralParser<NewsItem>() {

    override fun parseSingleObject(jsonObject: JSONObject): NewsItem {
        val id =
            if (hasKey(KEY_ID, jsonObject)) safeParseLong(KEY_ID, jsonObject)
            else -1L

        val slug =
            if (hasKey(KEY_SLUG, jsonObject)) safeParseString(KEY_SLUG, jsonObject)
            else null

        val title =
            if (hasKey(KEY_TITLE, jsonObject)) safeParseString(KEY_TITLE, jsonObject)
            else null

        val date =
            if (hasKey(KEY_DATE, jsonObject)) safeParseString(KEY_DATE, jsonObject)
            else ""

        val description =
            if (hasKey(KEY_DESCRIPTION, jsonObject)) safeParseString(KEY_DESCRIPTION, jsonObject)
            else null

        val link =
            if (hasKey(KEY_LINK, jsonObject)) safeParseString(KEY_LINK, jsonObject)
            else null

        val sticky =
            if (hasKey(KEY_STICKY, jsonObject)) safeParseBoolean(KEY_STICKY, jsonObject)
            else false

        val seo =
            if (hasKey(KEY_SEO, jsonObject)) {
                val seoJson = jsonObject.getJSONObject(KEY_SEO)
                SeoParser().parseSingleObject(seoJson)
            } else null

        val image =
            if (hasKey(KEY_IMAGE, jsonObject)) {
                val imageJson = jsonObject.getJSONObject(KEY_IMAGE)
                ImageParser().parseSingleObject(imageJson)
            } else null

        val thumbnail =
            if (hasKey(KEY_THUMBNAIL, jsonObject)) {
                val thumbnailJson = jsonObject.getJSONObject(KEY_THUMBNAIL)
                ThumbnailParser().parseSingleObject(thumbnailJson)
            } else null

        val author =
            if (hasKey(KEY_AUTHOR, jsonObject)) {
                val authorJson = jsonObject.getJSONObject(KEY_AUTHOR)
                AuthorParser().parseSingleObject(authorJson)
            } else null

        val category =
            if (hasKey(KEY_CATEGORY, jsonObject)) {
                val categoryJson = jsonObject.getJSONObject(KEY_CATEGORY)
                CategoryParser().parseSingleObject(categoryJson)
            } else null

        val topics =
            if (hasKey(KEY_TOPICS, jsonObject)) {
                val topicsJson = jsonObject.getJSONArray(KEY_TOPICS)
                TopicParser().parseObjectList(topicsJson)
            } else null

        val stocks =
            if (hasKey(KEY_STOCKS, jsonObject)) {
                val stocksJson = jsonObject.getJSONArray(KEY_STOCKS)
                StockParser().parseObjectList(stocksJson)
            } else null

        return NewsItem(id, title, slug, date, description, link, seo, image, thumbnail, author,
                            sticky, category, topics, stocks)
    }
}