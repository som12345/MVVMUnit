package com.example.mvvmunittesting.apihelper

import com.example.mvvmunittesting.model.Android
import com.example.mvvmunittesting.model.ApiResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApiService {
    @GET("android/jsonandroid")
    suspend fun getAndroidList(): Response<ApiResponseModel>

}