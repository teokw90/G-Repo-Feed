package com.kw.project.module.common.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(imageUrl: String) {
    if (imageUrl.isNotEmpty()) Glide.with(this.context).load(imageUrl).into(this)
}