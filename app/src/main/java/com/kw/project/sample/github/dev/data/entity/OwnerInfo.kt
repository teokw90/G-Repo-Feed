package com.kw.project.sample.github.dev.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

data class OwnerInfo(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Long,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("avatar_url") val avatarUrl: String
)