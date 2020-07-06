package com.example.week3.stringweek3.model

data class ForgotPassword(
    val code: Int=0,
    val message: String?= null,
    val status: Boolean
)