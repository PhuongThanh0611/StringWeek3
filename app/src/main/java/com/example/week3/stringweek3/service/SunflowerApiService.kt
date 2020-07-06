package com.example.week3.stringweek3.service

import com.example.week3.stringweek3.model.*

@Suppress("UNCHECKED_CAST")
class SunflowerApiService(private val api:ApiService) {
   // register
    fun getRegister(
        username:String,name:String,date:String,email:String,pass:String,confirmPass:String,
        onLoading: ()-> Unit,
        onSuccess:(Register)->Unit,
        onError:(String)->Unit
    ){
        val request = api.postRegister(username,name,date,email,pass,confirmPass)
        onLoading()
        ApiRequestHelper.asyncRequest(request, onSuccess as (Register?) -> Unit,onError)
    }
// login
    fun postLogin(email: String , pass: String,
                  onLoading: ()-> Unit,
                  onSuccess:(Register)->Unit,
                  onError:(String)->Unit){
        val requset=api.postLogin(email,pass)
    onLoading()
    ApiRequestHelper.asyncLogin(requset, onSuccess,onError)
    }
// forgotPass
    fun postForgotPassword(email: String,
                           onLoading: () -> Unit,
                           onSuccess: (ForgotPassword) -> Unit,
                           onError: (String) -> Unit){
        val request =api.postForgotPassword(email)
        onLoading()
        ApiRequestHelper.asyncForgotPass(request, onSuccess as (ForgotPassword?) ->Unit,onError)
    }
//intersets
    fun getIntersets(page: String , current_page : String , token : String,
                           onLoading: () -> Unit,
                           onSuccess: (Intersets) -> Unit,
                           onError: (String) -> Unit){
        val request =api.getIntersets(page , current_page,token )
        onLoading()
        ApiRequestHelper.asyncIntersets(request, onSuccess as (Intersets?) ->Unit,onError)
    }
// UserFollow
    fun getUserFollow(page: String , current_page : String , token : String,
                     onLoading: () -> Unit,
                     onSuccess: (UserFollow) -> Unit,
                     onError: (String) -> Unit){
        val request =api.getUserList(page,current_page,token)
        onLoading()
        ApiRequestHelper.asyncUserFollow(request, onSuccess as (UserFollow?) ->Unit,onError)
    }
// followUser
    fun postFollowUsers(id :Int , token : String,
                      onLoading: () -> Unit,
                      onSuccess: (FollowUsers) -> Unit,
                      onError: (String) -> Unit){
        val request =api.postFollowUsers(id,token)
        onLoading()
        ApiRequestHelper.asyncFollowUsers(request, onSuccess as (FollowUsers?) ->Unit,onError)
    }
// user Notification
    fun putUserNotification(likes: Boolean,comments:Boolean,new_followes: Boolean,post_saves:Boolean ,string :Boolean, token:String,
                        onLoading: () -> Unit,
                        onSuccess: (UserNotification) -> Unit,
                        onError: (String) -> Unit){
        val request =api.putUserNotification(likes,comments,new_followes,post_saves,string,token)
        onLoading()
        ApiRequestHelper.asyncUserNotification(request, onSuccess as (UserNotification?) ->Unit,onError)
    }
// home feed
    fun getFeed(page: String , current_page : String  , token : String,
                            onLoading: () -> Unit,
                            onSuccess: (FeedResponse) -> Unit,
                            onError: (String) -> Unit){
        val request =api.getFeed(page,current_page,token)
        onLoading()
        ApiRequestHelper.asyncFeed(request, onSuccess as (FeedResponse?) ->Unit,onError)
    }
// refresh token
    fun getRefershToken(token: String,
        onLoading: ()-> Unit,
        onSuccess:(Register)->Unit,
        onError:(String)->Unit
    ){
        val request = api.getRereshToken(token)
        onLoading()
        ApiRequestHelper.asyncRefreshToken(request, onSuccess as (Register?) -> Unit,onError)
    }

    // Logout
    fun getLogOut(token: String,
                        onLoading: ()-> Unit,
                        onSuccess:(LogOut)->Unit,
                        onError:(String)->Unit
    ){
        val request = api.getLogOut(token)
        onLoading()
        ApiRequestHelper.asyncLogout(request, onSuccess as (LogOut?) -> Unit,onError)
    }

}