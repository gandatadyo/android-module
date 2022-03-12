package com.aplikasi.androidmodule.ui.exam_http.examretrofit.response

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)