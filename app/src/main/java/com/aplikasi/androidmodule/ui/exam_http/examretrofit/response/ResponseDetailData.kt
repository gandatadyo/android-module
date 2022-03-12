package com.aplikasi.androidmodule.ui.exam_http.examretrofit.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailData(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)
