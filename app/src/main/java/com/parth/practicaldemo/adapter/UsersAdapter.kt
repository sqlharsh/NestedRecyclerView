package com.parth.practicaldemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parth.practicaldemo.api.responsemodel.User
import com.parth.practicaldemo.databinding.ListItemUsersBinding

internal class UsersAdapter(private var userList: MutableList<User>) :
    RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {
 private val viewPool = RecyclerView.RecycledViewPool()

    inner class MyViewHolder(var binding: ListItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root){

    }


    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview =
            ListItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.model = user

        if (!user.items.isNullOrEmpty()) {
            val imageAdapter = UserImagesAdapter(user.items as MutableList<String>)
            val linearLayoutManager = LinearLayoutManager(holder.binding.rcvUserImages.context,LinearLayoutManager.VERTICAL,false)
            linearLayoutManager.initialPrefetchItemCount = user.items.size
            val gridLayoutManager = GridLayoutManager(holder.binding.rcvUserImages.context, 2)
            gridLayoutManager.initialPrefetchItemCount = user.items.size
//            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//                override fun getSpanSize(position: Int): Int {
//                    when(position){
//                        0 -> return 1
//                        1 -> return 2
//                        else -> return 2
//                    }
//                }
//            }
            holder.binding.rcvUserImages.layoutManager = gridLayoutManager
            holder.binding.rcvUserImages.adapter = imageAdapter
            holder.binding.rcvUserImages.setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}