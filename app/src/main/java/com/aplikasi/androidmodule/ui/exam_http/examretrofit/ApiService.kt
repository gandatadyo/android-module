package com.aplikasi.androidmodule.ui.exam_http.examretrofit

import com.aplikasi.androidmodule.ui.exam_http.examretrofit.response.ResponseDetailData
import com.aplikasi.androidmodule.ui.exam_http.examretrofit.response.ResponseInfo
import com.aplikasi.androidmodule.ui.exam_http.examretrofit.response.ResponseListData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("list_data")
    fun getDataMovie(
    ): Call<ResponseListData>

    @GET("detail_data/{id}")
    fun getDetailMovie(
        @Path("id") id: String
    ): Call<ResponseDetailData>

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("insert_data")
    fun insertMovie(
        @Field("title") title: String,
    ): Call<ResponseInfo>

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("update_data")
    fun updateMovie(
        @Field("id") id: String,
        @Field("title") title: String,
    ): Call<ResponseInfo>

}