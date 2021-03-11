package com.parth.practicaldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.parth.practicaldemo.adapter.UsersAdapter
import com.parth.practicaldemo.api.requestmodel.UsertDataRequestModel
import com.parth.practicaldemo.api.responsemodel.User
import com.parth.practicaldemo.api.responsemodel.UsertDataResponseModel
import com.parth.practicaldemo.customview.EndlessRecyclerViewScrollListener
import com.parth.practicaldemo.databinding.ActivityMainBinding
import com.parth.practicaldemo.viewmodel.MainViewmodel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewmodel by viewModels<MainViewmodel>()
    private lateinit var adapter: UsersAdapter
    private val usersList:MutableList<User> = ArrayList()
    private var isLastPage = false
    private var initialCount = 0

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initView()
    }
    private fun initView(){

        viewmodel.requestModel = UsertDataRequestModel(offset = 1)
        binding.lifecycleOwner = this

        handlePagination()
        viewmodel.userDataResponse.observe(this,::handleUserDataResponse)
        binding.progresbar.visibility = View.VISIBLE
        viewmodel.getUsersList()
    }

    /**
     * This function handles setup of pagination
     */
    private fun handlePagination(){
        adapter = UsersAdapter(userList = usersList)
        binding.rcvUsers.adapter = adapter
        val layoutManager = LinearLayoutManager(this,)
        binding.rcvUsers.layoutManager = layoutManager
        val endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                binding.rcvUsers.post {
                    if (!isLastPage){
                        viewmodel.requestModel?.offset = viewmodel.requestModel?.offset?.plus(1)!!
                        viewmodel.getUsersList()
                    }
                }
            }
        }
        binding.rcvUsers.addOnScrollListener(endlessRecyclerViewScrollListener)

    }

    /**
     * This function handles api response
     */
    fun handleUserDataResponse(it: UsertDataResponseModel.Data?) {
        binding.progresbar.visibility = View.GONE
        if (it!=null){

            usersList.addAll(it.users)
            isLastPage = !it.hasMore
            adapter.notifyItemRangeInserted(initialCount,usersList.size)
            initialCount = usersList.size
        }
    }
}