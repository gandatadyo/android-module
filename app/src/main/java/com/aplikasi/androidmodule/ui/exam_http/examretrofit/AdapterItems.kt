package com.aplikasi.androidmodule.ui.exam_http.examretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.androidmodule.databinding.ItemAdapterTextBinding
import com.aplikasi.androidmodule.ui.exam_http.examretrofit.response.DataItem

class AdapterItems(private val listData: ArrayList<DataItem>, val clickLister: (DataItem) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


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
        fun bind(data: DataItem) {
            with(binding){
                lblName.text = data.title
                lblName.setOnClickListener {
                    clickLister(data)
                }
            }
        }
    }
}