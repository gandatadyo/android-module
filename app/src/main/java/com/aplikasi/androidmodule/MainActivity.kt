package com.aplikasi.androidmodule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.androidmodule.databinding.ActivityMainBinding
import com.aplikasi.androidmodule.databinding.ItemAdapterTextBinding
import com.aplikasi.androidmodule.ui.exam_backgroundprocess.ExamBackgroundProcess
import com.aplikasi.androidmodule.ui.exam_http.ExamHttp
import com.aplikasi.androidmodule.ui.exam_http.ExamRetrofit
import com.aplikasi.androidmodule.ui.exam_interface.ExamInterface
import com.aplikasi.androidmodule.ui.exam_reyclerviewloadmore.ExamRecyclerviewLoadMore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var listDataMenu = arrayListOf(
        "Background Process",
        "Http Api",
        "Retrofit",
        "Implementation Class Interface",
        "Recyclerview Load More",
    )
    private val adapterMenu = AdapterMenu(listDataMenu) {
        when (it) {
            "Background Process" -> {
                startActivity(Intent(this,ExamBackgroundProcess::class.java))
            }
            "Http Api" -> {
                startActivity(Intent(this,ExamHttp::class.java))
            }
            "Retrofit" -> {
                startActivity(Intent(this,ExamRetrofit::class.java))
            }
            "Implementation Class Interface" -> {
                startActivity(Intent(this,ExamInterface::class.java))
            }
            "Recyclerview Load More" -> {
                startActivity(Intent(this,ExamRecyclerviewLoadMore::class.java))
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.rvData.adapter = adapterMenu
    }

    inner class AdapterMenu(private val listData: ArrayList<String>, val clickLister: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemAdapter = ItemAdapterTextBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return CardViewHolder(itemAdapter)
        }

        override fun getItemCount(): Int = listData.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val holderTemp : CardViewHolder = holder as CardViewHolder
            listData[position].let { holderTemp.bind(it) }
        }

        inner class CardViewHolder(private val binding: ItemAdapterTextBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(data: String) {
                with(binding){
                    lblName.text = data
                    lblName.setOnClickListener {
                        clickLister(data)
                    }
                }
            }
        }
    }
}