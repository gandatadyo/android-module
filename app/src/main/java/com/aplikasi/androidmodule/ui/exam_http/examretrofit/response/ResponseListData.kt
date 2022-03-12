package com.aplikasi.androidmodule.ui.exam_http.examretrofit.response

import com.google.gson.annotations.SerializedName

data class ResponseListData(

	@field:SerializedName("data")
	val data: List<DataItem?>,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("message")
	val message: String
)