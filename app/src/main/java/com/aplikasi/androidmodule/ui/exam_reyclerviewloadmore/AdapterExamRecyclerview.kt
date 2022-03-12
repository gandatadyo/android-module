package com.aplikasi.androidmodule.ui.exam_reyclerviewloadmore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.androidmodule.databinding.ItemAdapterLoadingBinding
import com.aplikasi.androidmodule.databinding.ItemAdapterTextBinding
import com.aplikasi.androidmodule.utils.AdapterLoading

class AdapterExamRecyclerview(private val listData: ArrayList<String?>, val clickLister: (String) -> Unit ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewTypeItem = 0
    private val viewTypeLoading = 1

    override fun getItemViewType(position: Int): Int {
        return if(listData[position]!=null) viewTypeItem else viewTypeLoading
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            viewTypeItem -> {
                val itemAdapter = ItemAdapterTextBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
                return CardViewHolder(itemAdapter)
            }
            else -> {
                val itemAdapter = ItemAdapterLoadingBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
                AdapterLoading(itemAdapter)
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == viewTypeItem) {
            val holderTemp :CardViewHolder = holder as CardViewHolder
            listData[position]?.let { holderTemp.bind(it) }
        }
    }

    inner class CardViewHolder(private val binding: ItemAdapterTextBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            with(binding){
                lblName.text = data

                // event onclick
                lblName.setOnClickListener {
                    clickLister(data)
                }
            }
        }
    }
}