package com.parth.practicaldemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.parth.practicaldemo.api.responsemodel.UsertDataResponseModel
import com.parth.practicaldemo.databinding.ListItemUsersBinding

internal class UsersAdapter(private var userList: MutableList<UsertDataResponseModel.Data>) :
    RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(binding:ListItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemview = ListItemUsersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = userList[position]

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}