package com.example.week3.stringweek3.model

data class FollowUsers(
    val code: Int?,
    val data: DataFollowUser?,
    val message: String?,
    val status: Boolean?
)
data class DataFollowUser(
    val badge: Any?,
    val badgeID: Any?,
    val bio: Any?,
    val currentLocation: Any?,
    val date_of_birth: String?,
    val email: String?,
    val facebookID: Any?,
    val gender: Any?,
    val id: Int?,
    val isLoginFirst: Boolean?,
    val isNewUser: Boolean?,
    val isSuperUser: Boolean?,
    val name: String?,
    val notificationSettings: NotificationSettingsFollowUser?,
    val numberOfLogin: Int?,
    val profilePhoto: Any?,
    val trash: Boolean?,
    val type: String?,
    val username: String?,
    val wanderlust_details: Any?,
    val website: Any?
)
data class NotificationSettingsFollowUser(
    val comments: String?,
    val likes: String?,
    val new_followes: String?,
    val post_saves: String?,
    val string: String?
)