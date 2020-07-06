package com.example.week3.stringweek3.service

import com.example.week3.stringweek3.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("users-register-email")
    @FormUrlEncoded
    fun postRegister(
        @Field("username") username:String,
        @Field("name") name:String,
        @Field("date_of_birth") date_of_birth:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("confirm_password") confirm_password:String
    ): Call<Register>

    @POST("users-login")
    @FormUrlEncoded
    fun postLogin(
        @Field("username") email:String,
        @Field("password") password:String,
        @Field("fcm_token") fcm_token :String ="abc"
    ):Call<Register>

    @POST("users-forget-password")
    @FormUrlEncoded
    fun postForgotPassword(
        @Field("email") email:String
    ):Call<ForgotPassword>

    @GET("interest-categories-list")
    fun getIntersets(
        @Query("page") page : String,
        @Query("current_per_page") current_page: String,
        @Header("Authorization") token: String
    ):Call<Intersets>

    // user list follow
    @GET("users-list")
    fun getUserList(
        @Query("page") page:String,
        @Query("current_per_page") current_per_page:String,
        @Header("Authorization") token: String
    ):Call<UserFollow>

    //follow user
    @FormUrlEncoded
    @POST("follow-users")
    fun postFollowUsers(
        @Field("users_id") id:Int,
        @Header("Authorization") token: String

    ):Call<FollowUsers>

    //userNotification
    @PUT("users-notifications")
    fun putUserNotification(
        @Query("likes") like:Boolean,
        @Query("comments") comments:Boolean,
        @Query("new_followes") new_followes:Boolean,
        @Query("post_saves")post_saves:Boolean,
        @Query("string") string :Boolean,
        @Header("Authorization") token:String
    ):Call <UserNotification>

    // get homeFeed
    @GET("feed")
    fun getFeed(
        @Query("page") page:String,
        @Query("current_per_page") current_per_page:String,
        @Header("Authorization") token: String
    ):Call<FeedResponse>

    //refresh token
    @POST("users-refresh-token")
    fun getRereshToken(
        @Header("Authorizationrefresh") token: String
    ):Call<Register>

    //logout
    @POST("users-logout")
    fun getLogOut(
        @Header("Authorization") token: String
    ):Call<LogOut>

}