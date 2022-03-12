package com.aplikasi.androidmodule.ui.exam_http

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RestApi {
    private val errorMessageConnection = "Something wrong"
    val urlHost = "http://srisannano.com:63312"

    fun isOnlie(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    fun requestHttpGet(activity: Activity, urlServer: String, funcResponseSuccess: (String) -> Unit, funcResponseFailed: (String) -> Unit){
        if(!isOnlie(activity.baseContext)) {
            funcResponseFailed(errorMessageConnection)
        } else {
            val queue = Volley.newRequestQueue(activity.baseContext)
            val postRequest = object : StringRequest(
                Method.GET, urlServer,
                Response.Listener { response ->
                    funcResponseSuccess(response.toString())
                }, Response.ErrorListener {
                    funcResponseFailed(it.toString())
                }) {}
            queue.add(postRequest)
        }
    }

    fun requestHttpPost(activity: Activity, urlServer: String, paramsData: HashMap<String, String>, funcResponseSuccess: (String) -> Unit, funcResponseFailed: (String) -> Unit){
        if(!isOnlie(activity.baseContext)) {
            funcResponseFailed(errorMessageConnection)
        } else {
            val queue = Volley.newRequestQueue(activity.baseContext)
            val postRequest = object : StringRequest(
                Method.POST, urlServer,
                Response.Listener { response ->
                    funcResponseSuccess(response.toString())
                }, Response.ErrorListener {
                    funcResponseFailed(it.toString())
                }) {
                override fun getParams(): Map<String, String> {
                    return paramsData
                }
            }
            queue.add(postRequest)
        }
    }
}