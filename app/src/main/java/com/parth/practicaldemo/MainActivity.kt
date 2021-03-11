package com.parth.practicaldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.parth.practicaldemo.api.requestmodel.UsertDataRequestModel
import com.parth.practicaldemo.api.responsemodel.UsertDataResponseModel
import com.parth.practicaldemo.databinding.ActivityMainBinding
import com.parth.practicaldemo.viewmodel.MainViewmodel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewmodel by viewModels<MainViewmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initView()
    }
    private fun initView(){

        viewmodel.requestModel = UsertDataRequestModel(offset = 1)
        binding.lifecycleOwner = this

        viewmodel.userDataResponse.observe(this,::handleUserDataResponse)
        viewmodel.getUsersList()
    }

    /**
     * This functions handles api response
     */
    fun handleUserDataResponse(it: UsertDataResponseModel.Data?) {
        if (it!=null){

        }
    }
}