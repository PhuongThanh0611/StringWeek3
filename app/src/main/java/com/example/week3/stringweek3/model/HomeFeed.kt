package com.example.week3.stringweek3.model

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    val code: Int?,
    val data: List<FeedItem>?,
    val message: String?,
    val metadata: Metadata?,
    val status: Boolean?
)

data class FeedItem(
    @SerializedName("co_edit")
    val coEdit: Boolean?,
    val commentCounter: Int?,
    val copyCounter: Int?,
    val coverImage: CoverImage?,
    @SerializedName("created_at")
    val createdAt: String?,
    val description: String?,
    val id: Int?,
    val address:String ?,
    val isLiked: Boolean?,
    val isPrivate: Boolean?,
    val isSaved: Boolean?,
    val itineraries: List<Itinerary>?,
    val likeCounter: Int?,
    val photos: List<Photos>?,
    val place: Place?,
    val saveCounter: Int?,
    val strungCounter: Int?,
    val strungFrom: Any?,
    val strungFromID: Any?,
    val tags: List<Tag>?,
    val title: String?,
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val user: User?,
    val videos: Any?,
    val walkthrough: Int?
)
data class Photos(
    val id: Int?,
    val url: Url?
)


data class CoverImage(
    val id: Int?,
    val url: Url?
)

data class Itinerary(
    val id: Int?,
    val orderNo: Int?,
    val photos: PhotoX?,
    val poiID: Int?,
    val title: String?
)



data class Place(
    val address: String?,
    val copyCounter: Int?,
    val id: Int?,
    val lat: Float?,
    val long: Float?,
    val photo: Any?,
    val placeID: String?,
    val title: String?
)

data class Tag(
    val id: Int?,
    val title: String?
)

data class User(
    val badge: Any?,
    val badgeID: Any?,
    val bio: String?,
    val currentLocation: Any?,
    @SerializedName("dateOfBirth")
    val date_of_birth: String?,
    val email: String?,
    val facebookID: Any?,
    val gender: String?,
    val id: Int?,
    val isLoginFirst: Boolean?,
    val isNewUser: Boolean?,
    val isSuperUser: Boolean?,
    val name: String?,
    val notificationSettings: NotificationSettings?,
    val numberOfLogin: Int?,
    val profilePhoto: Any?,
    val trash: Boolean?,
    val type: String?,
    val username: String?,
    @SerializedName("wanderlust_details")
    val wanderlustDetails: WanderlustDetails?,
    val website: String?
)


data class UrlX(
    val medium: String?,
    val original: String?,
    val thumb: String?
)

data class PhotoX(
    val id: Int?,
    val url: UrlXX?
)

data class UrlXX(
    val medium: String?,
    val original: String?,
    val thumb: String?
)
