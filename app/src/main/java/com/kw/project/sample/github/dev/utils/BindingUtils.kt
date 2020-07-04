package com.kw.project.sample.github.dev.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

@BindingAdapter("ownerImage")
fun AppCompatImageView.setOwnerImage(imageUrl: String) {
    if (imageUrl.isNotEmpty()) Glide.with(this.context).load(imageUrl).into(this)
}