package com.aplikasi.androidmodule

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.androidmodule.databinding.ActivityMainBinding
import com.aplikasi.androidmodule.databinding.ItemAdapterTextBinding
import com.aplikasi.androidmodule.ui.InputDate
import com.aplikasi.androidmodule.ui.exam_activityresult.ExamActivityResult
import com.aplikasi.androidmodule.ui.exam_backgroundprocess.ExamBackgroundProcess
import com.aplikasi.androidmodule.ui.exam_http.ExamHttp
import com.aplikasi.androidmodule.ui.exam_http.examretrofit.ExamRetrofit
import com.aplikasi.androidmodule.ui.exam_interface.ExamInterface
import com.aplikasi.androidmodule.ui.exam_reyclerviewloadmore.ExamRecyclerviewLoadMore
import com.aplikasi.androidmodule.utils.ModuleGlobal

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var listDataMenu = arrayListOf(
        "Background Process",
        "Http Api",
        "Retrofit",
        "Implementation Class Interface",
        "Recyclerview Load More",
        "Input Date",
        "Activity Set Result",
    )

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            if(intent!=null){
                val sresult = intent.getStringExtra("hasil_result").toString()
                ModuleGlobal().showToast(this,sresult)
            }
            // Handle the Intent
        }
    }


    private val adapterMenu = AdapterMenu(listDataMenu) {
        when (it) {
            "Background Process" -> {
                startActivity(Intent(this,ExamBackgroundProcess::class.java))
            }
            "Http Api" -> {
                startActivity(Intent(this,ExamHttp::class.java))
            }
            "Retrofit" -> {
                startActivity(Intent(this, ExamRetrofit::class.java))
            }
            "Implementation Class Interface" -> {
                startActivity(Intent(this,ExamInterface::class.java))
            }
            "Recyclerview Load More" -> {
                startActivity(Intent(this,ExamRecyclerviewLoadMore::class.java))
            }
            "Input Date" -> {
                startActivity(Intent(this,InputDate::class.java))
            }
            "Activity Set Result Tambah" -> {
                val intent = Intent(this,ExamActivityResult::class.java)
                intent.putExtra("mode","tambah")
                startForResult.launch(intent)
            }
            "Activity Set Result Edit" -> {
                val intent = Intent(this,ExamActivityResult::class.java)
                intent.putExtra("mode","edit")
                startForResult.launch(intent)
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