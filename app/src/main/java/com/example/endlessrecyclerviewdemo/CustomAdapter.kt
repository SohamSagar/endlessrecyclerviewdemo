package com.example.endlessrecyclerviewdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.example.endlessrecyclerviewdemo.pagination.BaseRecyclerAdapter

import com.trufflepos.app.pagination.LoadMoreRecyclerAdapter

class CustomAdapter(
    data: List<String>
) : LoadMoreRecyclerAdapter<BaseRecyclerAdapter.ViewHolder?, String>(data) {

    private var clickFlag=0

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BaseRecyclerAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_phone_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindHolder(holder: ViewHolder?, position: Int) {
        holder!!.itemView.findViewById<AppCompatTextView>(R.id.txt_orderid).text = (position+1).toString()
    }

}