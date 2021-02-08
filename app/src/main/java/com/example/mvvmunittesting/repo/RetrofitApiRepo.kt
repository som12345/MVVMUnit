package com.example.mvvmunittesting.repo

import com.example.mvvmunittesting.apihelper.RetrofitApiHelper
import javax.inject.Inject

open class RetrofitApiRepo @Inject constructor(private val apiHelper: RetrofitApiHelper) {
    suspend fun getAndroidList() =  apiHelper.getAndroidList()

}