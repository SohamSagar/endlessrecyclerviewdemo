package com.example.endlessrecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.endlessrecyclerviewdemo.databinding.ActivityMainBinding
import com.trufflepos.app.pagination.EndlessRecyclerOnScrollListener

class MainActivity : AppCompatActivity() {
    var data = java.util.ArrayList<String>()
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")
        data.add("1")

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val lm = GridLayoutManager(this, 3)
        val adapter = CustomAdapter(data)
        binding.recyclerView.layoutManager = lm
        binding.recyclerView.adapter = adapter
//        binding.rv.layoutManager = GridLayoutManager(this, 3)
        val endlessRecyclerOnScrollListener = object : EndlessRecyclerOnScrollListener(
            lm
        ) {
            override fun onLoadMore(current_page: Int) {
                Log.e("soham", "onLoadMore: " )
                adapter!!.showLoadMore(true)
                Handler(Looper.getMainLooper()).postDelayed({
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    data.add("1")
                    adapter.notifyDataSetChanged()
                    adapter!!.showLoadMore(false)
                }, 3000)
            }
        }
        binding.recyclerView.setHasFixedSize(false)
        binding.recyclerView.setNestedScrollingEnabled(false)
        binding.recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener)
    }
}