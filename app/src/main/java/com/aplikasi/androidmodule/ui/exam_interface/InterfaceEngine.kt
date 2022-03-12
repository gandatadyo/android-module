package com.aplikasi.androidmodule.ui.exam_interface

interface InterfaceEngine {
    var nameEngine:String

    fun startEngine():String{
        return "start"
    }

    fun stopEngine():String{
        return "stop"
    }
}