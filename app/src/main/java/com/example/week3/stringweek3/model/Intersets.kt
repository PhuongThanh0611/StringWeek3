package com.example.week3.stringweek3.model

import com.google.gson.annotations.SerializedName

data class Intersets(
    val code: Int?,
    val data: List<DataIntersets>?,
    val message: String?,
    val metadata: Metadata?,
    val status: Boolean?
)
data class DataIntersets(
    @SerializedName("check_user_sellect")
    val checkUserSellect: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    val id: Int?,
    val photo: Photo?,
    val photoID: Int?,
    val title: String?,
    val trash: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    var statusItem: Boolean?
)
data class Photo(
    val id: Int?,
    val url: Url?
)
data class Metadata(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("current_per_page")
    val currentPerPage: String?,
    @SerializedName("next_page")
    val nextPage: Int?,
    @SerializedName("prev_pages")
    val prevPages: Int?,
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
)
data class Url(
    val medium: String?,
    val original: String?,
    val thumb: String?
)