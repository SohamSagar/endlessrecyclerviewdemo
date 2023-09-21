package com.example.endlessrecyclerviewdemo.pagination

import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.OnLongClickListener
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T : BaseRecyclerAdapter.ViewHolder?, M>(var list: List<M>) :
    RecyclerView.Adapter<T>() {

    fun getItem(position: Int): M {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    //Getter Methods for Resources
    fun getString(holder: ViewHolder, id: Int): String {
        return holder.itemView.context.getString(id)
    }

    fun getString(holder: ViewHolder, id: Int, vararg formatArgs: Any?): String {
        return holder.itemView.context.getString(id, *formatArgs)
    }

    fun getDrawable(holder: ViewHolder, id: Int): Drawable? {
        return ContextCompat.getDrawable(holder.itemView.context, id)
    }

    fun getColor(holder: ViewHolder, id: Int): Int {
        return ContextCompat.getColor(holder.itemView.context, id)
    }

    interface RecycleOnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    interface RecycleOnItemLongClickListener {
        fun onItemLongClick(view: View?, position: Int)
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView!!) {
        private var mRecycleOnItemClickListener: RecycleOnItemClickListener? = null
        private var mRecycleOnItemLongClickListener: RecycleOnItemLongClickListener? = null


        fun onItemClick(view: View?, position: Int) {
            if (mRecycleOnItemClickListener != null && position != -1) {
                mRecycleOnItemClickListener!!.onItemClick(view, position)
            }
        }

        fun onItemLongClick(view: View?, position: Int) {
            if (mRecycleOnItemLongClickListener != null) {
                mRecycleOnItemLongClickListener!!.onItemLongClick(view, position)
            }
        }


        fun setRecycleOnItemClickListener(mRecycleOnItemClickListener: RecycleOnItemClickListener?): ViewHolder {
            this.mRecycleOnItemClickListener = mRecycleOnItemClickListener
            return this
        }

        fun setRecyclerOnLongItemClickListener(mRecycleOnItemLongClickListener: RecycleOnItemLongClickListener?): ViewHolder {
            this.mRecycleOnItemLongClickListener = mRecycleOnItemLongClickListener
            return this
        }


        private val mOnClickListener = View.OnClickListener { v -> onItemClick(v, layoutPosition) }
        private val mLongClickListener = OnLongClickListener { view ->
            onItemLongClick(view, layoutPosition)
            false
        }

        //put here clickable views list
        fun clickableViews(vararg views: View) {
            for (view in views) {
                view.setOnClickListener(mOnClickListener)
            }
        }

        //put long clickable view here
        fun longClickableViews(vararg views: View) {
            for (view in views) {
                view.setOnLongClickListener(mLongClickListener)
            }
        }
    }

    /* ADDED BY_CT*/
    fun notifyItemChanged(item: M) {
        notifyItemChanged(list.indexOf(item))
    }


    fun updateList(newList: List<M>) {
        list = newList
        notifyDataSetChanged()
    }
}