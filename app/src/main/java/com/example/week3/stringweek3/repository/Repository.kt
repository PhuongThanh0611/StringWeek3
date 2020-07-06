package com.example.week3.stringweek3.repository

import com.example.week3.stringweek3.model.*

interface Repository {
    fun getRegister(username:String,name:String,date:String,email:String,pass:String,confirmPass:String):Result<Register>
    fun postLogin(email:String , pass:String):Result<Register>
    fun postForgotPasswprd(email: String):Result<ForgotPassword>
    fun getIntersets(page : String , current_page:String , token :String):Result<Intersets>
    fun getUserList(page : String , current_page:String , token :String):Result<UserFollow>
    fun postFollowUsers(id:Int ,token :String):Result<FollowUsers>
    fun putUserNotification(likes: Boolean,comments:Boolean,new_followes: Boolean,post_saves:Boolean ,string :Boolean, token:String):Result<UserNotification>
    fun getFeed(page : String , current_page:String , token :String): Result<FeedResponse>
    fun getRefreshToken(token: String):Result<Register>
    fun getLogOut(token: String):Result<LogOut>
}