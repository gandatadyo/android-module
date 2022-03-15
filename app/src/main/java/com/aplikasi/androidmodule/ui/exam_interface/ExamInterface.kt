package com.aplikasi.androidmodule.ui.exam_interface

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aplikasi.androidmodule.databinding.ActivityExamInterfaceBinding
import java.lang.Exception

class ExamInterface : AppCompatActivity() {
    private lateinit var binding: ActivityExamInterfaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamInterfaceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var sResult = ""
        val engineCar = EngineCar()
        engineCar.nameEngine = "Ferari"
        sResult+=engineCar.startEngine()
        sResult+=engineCar.stopEngine()
        binding.lblData.text = sResult

        binding.btnHttpApi.setOnClickListener {
            val httpApi = HttpApi(this,"www.google.com")
            httpApi.onSuccess()
            httpApi.onFailed()
        }

        val callbackHttpApi = CallbackHttpApi()
        callbackHttpApi.requestHttpGet("http",object:RequestApi.ResponseApi{
            override fun onSuccess(result:String) {
                Toast.makeText(this@ExamInterface,"Success",Toast.LENGTH_LONG).show()
            }

            override fun onFailed(error:String) {
                Toast.makeText(this@ExamInterface,"Failed",Toast.LENGTH_LONG).show()
            }
        })


    }
}

class CallbackHttpApi :RequestApi{
    override fun requestHttpGet(url: String, callback: RequestApi.ResponseApi) {
        try {
            callback.onSuccess("failed")
        }catch (e:Exception){
            callback.onSuccess("failed")
        }
        super.requestHttpGet(url, callback)
    }
}

class EngineCar() :InterfaceEngine {
    override var nameEngine: String = ""

    override fun startEngine(): String {
        return "\n$nameEngine ${super.startEngine()}"
    }

    override fun stopEngine(): String {
        return "\n$nameEngine ${super.stopEngine()}"
    }
}

class HttpApi(
    private val context:Context,
    override val url: String
) :InterfaceHttp {

    override fun onSuccess() {
        Toast.makeText(context, "Success :$url",Toast.LENGTH_LONG).show()
    }

    override fun onFailed() {
        Toast.makeText(context, "Failed $url",Toast.LENGTH_LONG).show()
    }
}