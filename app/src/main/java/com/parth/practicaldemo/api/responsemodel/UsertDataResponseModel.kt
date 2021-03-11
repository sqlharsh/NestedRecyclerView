package com.parth.practicaldemo.api.responsemodel


import com.google.gson.annotations.SerializedName

data class UsertDataResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: Any,
    @SerializedName("status")
    val status: Boolean
){
    data class Data(
        @SerializedName("has_more")
        val hasMore: Boolean,
        @SerializedName("users")
        val users: List<User>
    )
}