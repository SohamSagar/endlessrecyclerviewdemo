package com.trufflepos.app.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.endlessrecyclerviewdemo.R
import com.example.endlessrecyclerviewdemo.pagination.BaseRecyclerAdapter

abstract class LoadMoreRecyclerAdapter<T : BaseRecyclerAdapter.ViewHolder?, M>(data: List<M>?) :
    BaseRecyclerAdapter<T, M>(data!!) {
    private val VIEW_TYPE_FOOTER = 1
    private val VIEW_TYPE_ITEM = 2
    private var showLoadMore = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return if (viewType == VIEW_TYPE_FOOTER && showLoadMore) {
            LoaderHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
            ) as T
        } else onCreateHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        if (position == itemCount - 1 && showLoadMore) {
            (holder as LoaderHolder).linProgressView.visibility =
                if (showLoadMore) View.VISIBLE else View.GONE
            return
        }
        onBindHolder(holder, position)
    }

    //This methods for created for making loadmore recyclerview
    abstract fun onCreateHolder(parent: ViewGroup?, viewType: Int): T
    abstract fun onBindHolder(holder: T, position: Int)

    override fun getItemCount(): Int {
        if (list.size == null || list.size <= 0) {
            return 0
        }
        return if (showLoadMore) {
            list.size + 1
        } else {
            list.size
        }
    }

    fun showLoadMore(showLoadMore: Boolean) {

        //Return if already shown/hide
        if (this.showLoadMore == showLoadMore) {
            return
        }

        this.showLoadMore = showLoadMore
        if (showLoadMore) {
            notifyItemInserted(itemCount - 1)
        } else {
            notifyItemRemoved(itemCount)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1 && showLoadMore) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    class LoaderHolder(itemView: View) : ViewHolder(itemView) {
        var linProgressView = itemView.findViewById(R.id.progress) as View
    }
}