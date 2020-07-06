package com.example.week3.stringweek3.model

data class UserNotification(
    val code: Int?,
    val data: DataUserNotification?,
    val message: String?,
    val status: Boolean?
)
data class DataUserNotification(
    val access_token: String?,
    val badge: Any?,
    val bio: Any?,
    val created_at: String?,
    val currentLocation: Any?,
    val date_of_birth: String?,
    val email: String?,
    val facebookID: Any?,
    val fcm_token: String?,
    val gender: Any?,
    val id: Int?,
    val isActive: Boolean?,
    val isLoginFirst: Boolean?,
    val isNewUser: Boolean?,
    val isSuperUser: Boolean?,
    val name: String?,
    val notificationSettings: NotificationSettingsUserNotification?,
    val numberOfLogin: Int?,
    val profilePhoto: Any?,
    val refresh_token: String?,
    val trash: Boolean?,
    val type: String?,
    val updated_at: String?,
    val username: String?,
    val wanderlust_details: Any?,
    val website: Any?
)
data class NotificationSettingsUserNotification(
    val comments: String?,
    val likes: String?,
    val new_followes: String?,
    val post_saves: String?,
    val string: String?
)