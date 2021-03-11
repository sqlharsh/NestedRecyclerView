package com.parth.practicaldemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parth.practicaldemo.api.ApiConstants
import com.parth.practicaldemo.api.ApiHelperClass
import com.parth.practicaldemo.api.requestmodel.UsertDataRequestModel
import com.parth.practicaldemo.api.responsemodel.UsertDataResponseModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

object MainRepository {

    fun getUsersListing(requestModel: UsertDataRequestModel?): LiveData<UsertDataResponseModel.Data?> {
        val data = MutableLiveData<UsertDataResponseModel.Data?>()

        val call =
            requestModel?.offset?.let { ApiHelperClass.getApiClient().getUsers(offset = it,limit = requestModel.limit) }
        call?.enqueue(object : Callback, retrofit2.Callback<UsertDataResponseModel> {
            override fun onResponse(
                call: Call<UsertDataResponseModel>,
                response: Response<UsertDataResponseModel>
            ) {
                if (response?.body() != null){
                    data.value = null
                    return
                }
                if (response.body()?.status == true){
                    data.value = response.body()?.data
                }
            }

            override fun onFailure(call: Call<UsertDataResponseModel>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}