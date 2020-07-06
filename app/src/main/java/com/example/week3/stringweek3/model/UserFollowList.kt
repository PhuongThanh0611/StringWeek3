package com.example.week3.stringweek3.model

import com.google.gson.annotations.SerializedName

data class UserFollow(
    val code: Int,
    val data: List<DataUserFollow>,
    val message: String,
    val metadata: MetadataUserFollow,
    val status: Boolean
)
data class DataUserFollow(
    val badge: Any?,
    val badgeID: Any?,
    val bio: String?,
    var checkfollow: Boolean?,
    @SerializedName("created_at")
    val createdAt: String?,
    val currentLocation: Any?,
    val email: String?,
    val facebookID: String?,
    val gender: String?,
    val id: Int?,
    val isBlocked: Boolean?,
    val isLoginFirst: Boolean?,
    val isSuggestFollower: Int?,
    val isSuperUser: Boolean?,
    val notificationSettings: NotificationSettings?,
    val numberOfLogin: Int?,
    val photos: List<PhotoUserFollow>?,
    val profilePhoto: String?,
    val trash: Boolean?,
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val username: String?,
    val website: Any?
)
data class PhotoUserFollow(
    val id: Int?,
    val url: UrlUserFollow?
)
data class UrlUserFollow(
    val medium: String?,
    val original: String?,
    val thumb: String?
)

data class MetadataUserFollow(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("current_per_page")
    val currentPerPage: String,
    @SerializedName("next_page")
    val nextPage: Int?,
    @SerializedName("prev_pages")
    val prevPages: Int?,
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
)
data class NotificationSettings(
    @SerializedName("app_update")
    val appUpdate: String?,
    val comments: Any?,
    @SerializedName("contact_joins_string")
    val contactJoinsString: String?,
    val likes: Any?,
    @SerializedName("near_you")
    val nearYou: String?,
    @SerializedName("new_followes")
    val newFollowes: Any?,
    @SerializedName("post_saves")
    val postSaves: Any?,
    val processor: String?,
    val string: Any?
)