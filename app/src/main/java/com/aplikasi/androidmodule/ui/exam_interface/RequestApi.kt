package com.aplikasi.androidmodule.ui.exam_interface

interface RequestApi {

    fun requestHttpGet(url:String,callback:ResponseApi){

    }

    interface ResponseApi{
        fun onSuccess(e:String){

        }
        fun onFailed(e:String){

        }
    }
}