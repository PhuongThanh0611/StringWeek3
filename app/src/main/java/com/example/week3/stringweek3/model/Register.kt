package com.example.week3.stringweek3.model

import com.google.gson.annotations.SerializedName

data class Register(
    val code: Int?,
    val data:Data?,
    val message: String?,
    val status: Boolean?
)

data class Data(
    @SerializedName("access_token")
    val accessToken: String?,
    val badgeID: Int?,
    val bio: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    val currentLocation: String?,
    val email: String?,
    val facebookID: String?,
    val gender: String?,
    val id: Int?,
    val isActive: Boolean?,
    val isSuperUser: Boolean?,
    val profilePhoto: String?,
    @SerializedName("refresh_token")
    val refreshToken: String?,
    val trash: Boolean?,
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val username: String?,
    @SerializedName("wanderlust_details")
    val wanderlustDetails:WanderlustDetails?,
    val website: String?
)
data class WanderlustDetails(
    val isWanderlust: Boolean?,
    val location: Location?
)
data class Location(
    val description: String?,
    val lat: Float?,
    val long: Float?,
    val name: String?,
    val placeID: String?
)
