package com.parth.practicaldemo.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.parth.practicaldemo.R
import com.parth.practicaldemo.api.customview.CircleImageView

@BindingAdapter("srcImage")
fun setImageResource(imageView: AppCompatImageView,url:String?){
    if (!url.isNullOrEmpty()){
        imageView.load(url){
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
        }
    }
}