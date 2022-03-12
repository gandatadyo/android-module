package com.aplikasi.androidmodule.ui.exam_http

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.androidmodule.databinding.ActivityHttpApiBinding
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ExamHttp : AppCompatActivity() {
    private lateinit var binding: ActivityHttpApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpApiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // using library volley. https://github.com/google/volley

        binding.btnHttpConnection.setOnClickListener {
            val connection = URL("http://www.android.com/").openConnection() as HttpURLConnection
            try {
                val data = connection.inputStream.bufferedReader().use { it.readText() }
                // ... do something with "data"
                binding.lblData.text = data
            } finally {
                connection.disconnect()
            }
        }

        binding.btnVolleyGet.setOnClickListener {
            RestApi().requestHttpGet(this,"https://my-json-server.typicode.com/gandatadyo/fake-server",{
                binding.lblData.text = it
            },{
                binding.lblData.text = it
            })
        }

        binding.btnVolleyPost.setOnClickListener {
            val params = HashMap<String,String>()
            params["data"]="ganda"
            RestApi().requestHttpPost(this,"http://192.168.1.11:3000/data",params,{
                binding.lblData.text = it
            },{
                binding.lblData.text = it
            })
        }
    }
}

