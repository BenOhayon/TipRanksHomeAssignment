package com.benohayon.tiprankshomeassignment.model

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benohayon.tiprankshomeassignment.PAGINATION_PAGE_SIZE
import com.benohayon.tiprankshomeassignment.PAGINATION_LIST_SCROLL_THRESHOLD

abstract class NewsItemsListScrollListener(
    private val mLayoutManager: LinearLayoutManager,
) : RecyclerView.OnScrollListener() {

    private val mItemsThreshold: Int = PAGINATION_LIST_SCROLL_THRESHOLD
    private val mPageSize: Int = PAGINATION_PAGE_SIZE

    /**
     * True when the data was requested
     */
    private var isPendingData = false

    /**
     * False when the end of endless data reached
     */
    private var mHaveMoreData = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount = mLayoutManager.itemCount
        val lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
        Log.d(
            "SCROLL_TEST", "onScrolled: mHaveMoreData = " + mHaveMoreData + ", isPendingData = " +
                    isPendingData + ", lastVisibleItem + mItemsThreshold >= totalItemCount = " + (lastVisibleItem + mItemsThreshold >= totalItemCount)
        )
        if (mHaveMoreData && !isPendingData && lastVisibleItem + mItemsThreshold >= totalItemCount) {
            //We are close to bottom, time to load next portion
            val nextPageNum = totalItemCount / mPageSize + 1
            requestData(nextPageNum)
            isPendingData = true
        }
    }

    fun setHaveMoreData(haveMoreData: Boolean) {
        mHaveMoreData = haveMoreData
    }

    fun notifyDataLoaded() {
        isPendingData = false
    }

    protected abstract fun requestData(pageNum: Int)
}