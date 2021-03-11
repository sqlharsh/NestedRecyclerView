package com.parth.practicaldemo.api.requestmodel


import com.google.gson.annotations.SerializedName

data class UsertDataRequestModel(
    var offset: Int,
    val limit: Int = 10
)