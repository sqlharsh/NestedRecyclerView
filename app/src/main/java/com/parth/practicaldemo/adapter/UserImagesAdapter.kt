package com.parth.practicaldemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.parth.practicaldemo.api.responsemodel.User
import com.parth.practicaldemo.api.responsemodel.UsertDataResponseModel
import com.parth.practicaldemo.databinding.ListItemUserImagesBinding
import com.parth.practicaldemo.databinding.ListItemUsersBinding

internal class UserImagesAdapter(private var imageList: MutableList<String>) :
    RecyclerView.Adapter<UserImagesAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding:ListItemUserImagesBinding) : RecyclerView.ViewHolder(binding.root)

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemview = ListItemUserImagesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val image = imageList[position]
        holder.binding.image = image
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}