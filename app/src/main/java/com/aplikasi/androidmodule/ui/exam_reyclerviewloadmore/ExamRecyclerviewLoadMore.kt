package com.aplikasi.androidmodule.ui.exam_reyclerviewloadmore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.androidmodule.databinding.ActivityExamRecyclerviewLoadMoreBinding

class ExamRecyclerviewLoadMore : AppCompatActivity() {
    private lateinit var binding: ActivityExamRecyclerviewLoadMoreBinding

    private val listData = arrayListOf<String?>()
    private var recnoData = 0
    private var adapterData = AdapterExamRecyclerview(listData){
        // event onclick
    }

    // variable for pagination
    private lateinit var linearLayoutManager : LinearLayoutManager
    private var indexPagging = 1
    private var ctPagging = 10 // maksimal mengambil data sebanyak 10 kalo
    private var flagCanGetData = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamRecyclerviewLoadMoreBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvData.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rvData.layoutManager = linearLayoutManager
        binding.rvData.adapter = adapterData
        binding.rvData.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if((indexPagging<=ctPagging) && flagCanGetData) {
                    if (linearLayoutManager.findLastVisibleItemPosition() >= linearLayoutManager.itemCount - 1) {
                        flagCanGetData = false
                        listData.add(null)
                        adapterData.notifyItemInserted(listData.size - 1)
                        Handler(Looper.getMainLooper()).postDelayed({
                            getData()
                        }, 2000)
                    }
                }
            }
        })

        getData()
    }

    private fun getData(){
        flagCanGetData = false
        Handler(Looper.getMainLooper()).postDelayed({
            if(listData.size>0 && indexPagging!=1){
                listData.removeAt(listData.size - 1)
                adapterData.notifyItemRemoved(listData.size)
            }

            for (i in 0 until 10){
                recnoData += 1
                listData.add("Data ${recnoData}")
            }
            adapterData.notifyDataSetChanged()
            indexPagging += 1
            flagCanGetData = true
        }, 3000)
     }
}