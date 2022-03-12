package com.aplikasi.androidmodule.ui.exam_http.examretrofit.response

import com.google.gson.annotations.SerializedName

data class ResponseInfo(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
