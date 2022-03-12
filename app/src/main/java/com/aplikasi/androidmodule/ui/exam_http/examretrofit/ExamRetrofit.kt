package com.aplikasi.androidmodule.ui.exam_http.examretrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.androidmodule.databinding.ActivityExamRetofitBinding
import com.aplikasi.androidmodule.ui.exam_http.examretrofit.response.DataItem
import com.aplikasi.androidmodule.ui.exam_http.examretrofit.response.ResponseInfo
import com.aplikasi.androidmodule.ui.exam_http.examretrofit.response.ResponseListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExamRetrofit : AppCompatActivity() {
    private lateinit var binding: ActivityExamRetofitBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    private var listDataMovie = arrayListOf<DataItem>()
    private var adapterMovie = AdapterItems(listDataMovie){

    }

    private var recno = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamRetofitBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSave.setOnClickListener { addDataMovie() }

        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.rvData.adapter = adapterMovie

        getListDataMovie()
    }

    private fun getListDataMovie(){
        ApiConfig.getApiService().getDataMovie().enqueue(object : Callback<ResponseListData> {
            override fun onResponse(
                call: Call<ResponseListData>,
                response: Response<ResponseListData>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody!!.status=="true") {
                        listDataMovie.clear()
                        if (responseBody != null) {
                            for (i in 0 until responseBody.data.size) {
                                listDataMovie.add(
                                    responseBody.data[i]!!
                                )
                            }
                        }
                        adapterMovie.notifyDataSetChanged()
                    }else{
                        Toast.makeText(this@ExamRetrofit, responseBody.message,Toast.LENGTH_LONG).show()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseListData>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun addDataMovie(){
        val ctx = this
        ApiConfig.getApiService().insertMovie(
            binding.edtTitle.text.toString()
        ).enqueue(object : Callback<ResponseInfo> {
            override fun onResponse(
                call: Call<ResponseInfo>,
                response: Response<ResponseInfo>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody!!.status=="true") {
                        Toast.makeText(ctx, responseBody.message,Toast.LENGTH_LONG).show()
                        getListDataMovie()
                    }else{
                        Toast.makeText(ctx, responseBody.message,Toast.LENGTH_LONG).show()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseInfo>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun editDataMovie(){
        val ctx = this
        val client = ApiConfig.getApiService().updateMovie(
            recno.toString(),
            binding.edtTitle.text.toString()
        )
        client.enqueue(object : Callback<ResponseInfo> {
            override fun onResponse(
                call: Call<ResponseInfo>,
                response: Response<ResponseInfo>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody!!.status=="true") {
                        Toast.makeText(ctx, responseBody.message,Toast.LENGTH_LONG).show()
                        getListDataMovie()
                    }else{
                        Toast.makeText(ctx, responseBody.message,Toast.LENGTH_LONG).show()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseInfo>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

}