package com.benohayon.tiprankshomeassignment.model.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.benohayon.tiprankshomeassignment.R
import com.benohayon.tiprankshomeassignment.model.models.NewsItem

class NewsItemsAdapter : ListAdapter<NewsItem, NewsItemsAdapter.NewsItemViewHolder>(DiffUtilCallback()) {

    interface NewsItemsAdapterListener {
        fun onItemClick(link: String?)
        fun onPromote()
    }

    var newsItemsAdapterListener: NewsItemsAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news_post, parent)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.setTitle(newsItem.title)
        holder.setSubtitle(newsItem.date)
        
        holder.itemView.setOnClickListener {
            newsItemsAdapterListener?.onItemClick(newsItem.link)
        }

        if (position % 10 == 3)
            holder.promote()
    }

    inner class NewsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var titleTv: TextView? = null
        private var subtitleTv: TextView? = null
        private var promotionBarTv: TextView? = null

        init {
            titleTv = view.findViewById(R.id.newsPostListItemTitle)
            subtitleTv = view.findViewById(R.id.newsPostListItemSubtitle)
            promotionBarTv = view.findViewById(R.id.newsPostListItemPromotionBar)
        }

        fun setTitle(title: String?) {
            titleTv?.text = title
        }

        fun setSubtitle(subtitle: String?) {
            subtitleTv?.text = subtitle
        }

        fun promote() {
            promotionBarTv?.visibility = View.VISIBLE
            newsItemsAdapterListener?.onPromote()
        }
    }
}

private class DiffUtilCallback : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
        oldItem == newItem

}