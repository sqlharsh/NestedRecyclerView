package com.parth.practicaldemo.api

import com.parth.practicaldemo.api.responsemodel.UsertDataResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCallInterface {
    @GET(ApiConstants.API_USERS)
    fun getUsers(@Query("offset") offset:Int, @Query("limit") limit:Int) : Call<UsertDataResponseModel>
}