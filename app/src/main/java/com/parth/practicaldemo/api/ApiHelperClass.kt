package com.parth.practicaldemo.api

import com.parth.practicaldemo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiHelperClass private constructor() {
    private var apiCallInterface:ApiCallInterface

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)

        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiCallInterface = builder.create(ApiCallInterface::class.java)
    }

    companion object{
        private var apiClient:ApiCallInterface? = null
        fun getApiClient():ApiCallInterface{
           if (apiClient == null)
               apiClient = ApiHelperClass().apiCallInterface

            return apiClient as ApiCallInterface
        }
    }
}