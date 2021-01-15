package com.kw.project.module.features.home.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("ownerImage")
fun AppCompatImageView.setOwnerImage(imageUrl: String) {
    if (imageUrl.isNotEmpty()) Glide.with(this.context).load(imageUrl).into(this)
}