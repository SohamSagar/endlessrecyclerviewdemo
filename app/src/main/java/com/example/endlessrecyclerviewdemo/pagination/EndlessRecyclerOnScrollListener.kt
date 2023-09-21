package com.trufflepos.app.pagination

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold = 10 // The minimum amount of items to have below your current scroll position before loading more.
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    private val startingPageIndex = 1
    private var current_page = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        Log.e("MMM:0",dx.toString()+"***"+dy.toString())
        Log.e("MMM:1",previousTotal.toString()+"***"+visibleThreshold.toString()+"***"+mLinearLayoutManager.itemCount.toString())
        if (dy > 0) //check for scroll down
        {
            visibleItemCount = recyclerView.childCount
            totalItemCount = mLinearLayoutManager.itemCount
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
            Log.e("soham", "visibleItemCount = $visibleItemCount totalItemCount = $totalItemCount firstVisibleItem = $firstVisibleItem previousTotal = $previousTotal")
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }

            //9 9
            if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                // End has been reached

                // Do something
                current_page++
                onLoadMore(current_page)
                loading = true
            }
        } else if (mLinearLayoutManager.findFirstVisibleItemPosition() < 4) {
            //onShowTop(false);
        }
    }

    abstract fun onLoadMore(current_page: Int)

    // Call whenever performing new searches
    fun resetState() {
        current_page = startingPageIndex
        previousTotal = 0
        loading = true
    }

    fun previousState() {
        current_page = current_page - 1
        previousTotal = 0
        loading = true
    }

    companion object {
        var TAG = EndlessRecyclerOnScrollListener::class.java.simpleName
    }
}