package com.example.mvvmunittesting.apihelper

import com.example.mvvmunittesting.model.ApiResponseModel
import retrofit2.Response
import javax.inject.Inject

class RetrofitApiHelperImpl @Inject constructor(private val apiService: RetrofitApiService) :
    RetrofitApiHelper {
    override suspend fun getAndroidList(): Response<ApiResponseModel> =
        apiService.getAndroidList()

}