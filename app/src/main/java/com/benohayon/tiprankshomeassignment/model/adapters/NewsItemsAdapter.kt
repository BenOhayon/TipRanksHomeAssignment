package com.benohayon.tiprankshomeassignment.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.benohayon.tiprankshomeassignment.PROMOTED_ITEM_OFFSET
import com.benohayon.tiprankshomeassignment.R
import com.benohayon.tiprankshomeassignment.model.models.NewsItem

class NewsItemsAdapter(var newsItemsAdapterListener: NewsItemsAdapterListener? = null)
    : ListAdapter<NewsItem, NewsItemsAdapter.NewsItemViewHolder>(DiffUtilCallback()) {

    interface NewsItemsAdapterListener {
        fun onItemClick(link: String?)
        fun onPromote()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news_post, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(holder.adapterPosition)
        holder.reset()
        holder.setTitle(newsItem.title)
        holder.setSubtitle(newsItem.date)

        holder.itemView.setOnClickListener {
            newsItemsAdapterListener?.onItemClick(newsItem.link)
        }

        if ((holder.adapterPosition + 1) % 10 == PROMOTED_ITEM_OFFSET)
            holder.promote()
    }

    fun updateList(newsItemsList: List<NewsItem>) {
        submitList(newsItemsList)
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

        fun reset() {
            titleTv?.text = ""
            subtitleTv?.text = ""
            promotionBarTv?.visibility = View.GONE
        }

        fun setTitle(title: String?) {
            titleTv?.text = title
        }

        fun setSubtitle(subtitle: String?) {
            subtitleTv?.text = subtitle
        }

        fun promote() {
            promotionBarTv?.visibility = View.VISIBLE
            promotionBarTv?.setOnClickListener {
                newsItemsAdapterListener?.onPromote()
            }
        }
    }
}

private class DiffUtilCallback : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
        oldItem == newItem

}