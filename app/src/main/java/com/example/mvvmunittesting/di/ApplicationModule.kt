package com.example.mvvmunittesting.di

import com.example.mvvmunittesting.apihelper.RetrofitApiHelper
import com.example.mvvmunittesting.apihelper.RetrofitApiHelperImpl
import com.example.mvvmunittesting.apihelper.RetrofitApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {
    @Provides
    fun provideBaseUrl() = "https://api.learn2crack.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RetrofitApiService =
        retrofit.create(RetrofitApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: RetrofitApiHelperImpl): RetrofitApiHelper = apiHelper

}