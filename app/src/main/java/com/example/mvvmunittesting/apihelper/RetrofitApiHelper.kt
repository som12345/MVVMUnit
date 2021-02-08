package com.example.mvvmunittesting.apihelper

import com.example.mvvmunittesting.model.Android
import com.example.mvvmunittesting.model.ApiResponseModel
import retrofit2.Response

interface RetrofitApiHelper {
    suspend fun getAndroidList(): Response<ApiResponseModel>
}