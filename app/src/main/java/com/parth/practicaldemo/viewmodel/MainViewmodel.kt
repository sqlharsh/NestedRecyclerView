package com.parth.practicaldemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.parth.practicaldemo.api.requestmodel.UsertDataRequestModel
import com.parth.practicaldemo.repository.MainRepository

class MainViewmodel : ViewModel() {

    var requestModel: UsertDataRequestModel? = null
    private val usersLiveData = MutableLiveData<UsertDataRequestModel>()

    val userDataResponse = usersLiveData.switchMap {
        MainRepository.getUsersListing(requestModel = it)
    }

    fun getUsersList() {
        usersLiveData.value = requestModel
    }
}