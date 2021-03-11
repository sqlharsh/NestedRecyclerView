package com.parth.practicaldemo.api.requestmodel


import com.google.gson.annotations.SerializedName

data class UsertDataRequestModel(
    val offset: Int,
    val limit: Int = 10
)