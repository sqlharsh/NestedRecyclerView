package com.parth.practicaldemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parth.practicaldemo.api.responsemodel.User
import com.parth.practicaldemo.databinding.ListItemUsersBinding

internal class UsersAdapter(private var userList: MutableList<User>) :
    RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: ListItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root)

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
            val gridLayoutManager = GridLayoutManager(holder.binding.rcvUserImages.context, 2)
//            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//                override fun getSpanSize(position: Int): Int {
//                    when(position){
//                        0 -> return 1
//                        1 -> return 2
//                        else -> return 2
//                    }
//                }
//            }
            holder.binding.rcvUserImages.setHasFixedSize(true)
            holder.binding.rcvUserImages.layoutManager = gridLayoutManager
            holder.binding.rcvUserImages.adapter = imageAdapter
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}