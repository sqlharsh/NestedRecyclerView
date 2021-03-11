package com.parth.practicaldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.parth.practicaldemo.adapter.UsersAdapter
import com.parth.practicaldemo.api.requestmodel.UsertDataRequestModel
import com.parth.practicaldemo.api.responsemodel.User
import com.parth.practicaldemo.api.responsemodel.UsertDataResponseModel
import com.parth.practicaldemo.databinding.ActivityMainBinding
import com.parth.practicaldemo.viewmodel.MainViewmodel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewmodel by viewModels<MainViewmodel>()
    private lateinit var adapter: UsersAdapter
    private val usersList:MutableList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initView()
    }
    private fun initView(){

        viewmodel.requestModel = UsertDataRequestModel(offset = 1)
        binding.lifecycleOwner = this

        adapter = UsersAdapter(userList = usersList)
        binding.rcvUsers.adapter = adapter
        viewmodel.userDataResponse.observe(this,::handleUserDataResponse)
        binding.progresbar.visibility = View.VISIBLE
        viewmodel.getUsersList()
    }

    /**
     * This functions handles api response
     */
    fun handleUserDataResponse(it: UsertDataResponseModel.Data?) {
        binding.progresbar.visibility = View.GONE
        if (it!=null){
            usersList.addAll(it.users)
            adapter.notifyDataSetChanged()
        }
    }
}