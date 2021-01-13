package com.kw.project.module.core.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Kuan Wah Teo on 02/05.2020
 */
data class RepositoryInfo(
    @SerializedName("id") val id: Long,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("private") val isPrivate: Boolean,
    @SerializedName("owner") val owner: OwnerInfo,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String
)
