package com.example.week3.stringweek3.service

import com.example.week3.stringweek3.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiRequestHelper {
// register
    inline fun <T> asyncRequest(
        request: Call<T>,
        crossinline onSuccess: (Register) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    if((response.body() as? Register)?.data == null){
                        onError("error message: ${(response.body() as? Register)?.message}")
                    }
                    (response.body() as? Register)?.data?.let {
                            onSuccess(response.body() as Register)
                    }
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }
    // login
    inline fun <T> asyncLogin(
        request: Call<T>,
        crossinline onSuccess: (Register) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    if((response.body() as? Register)?.data == null){
                        onError(" ${(response.body() as? Register)?.message}")
                    }
                    (response.body() as? Register)?.data?.let {
                        onSuccess(response.body() as Register)
                    }
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }

    // forgotPassword
    inline fun <T> asyncForgotPass(
        request: Call<T>,
        crossinline onSuccess: (ForgotPassword?) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body() as? ForgotPassword)
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }
// Intersets
    inline fun <T> asyncIntersets(
        request: Call<T>,
        crossinline onSuccess: (Intersets?) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body() as? Intersets)
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }
// UserFollow
    inline fun <T> asyncUserFollow(
        request: Call<T>,
        crossinline onSuccess: (UserFollow?) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body() as? UserFollow)
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }
// followUser
    inline fun <T> asyncFollowUsers(
        request: Call<T>?,
        crossinline onSuccess: (FollowUsers?) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request?.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body() as? FollowUsers)
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }
//Notification
    inline fun <T> asyncUserNotification(
        request: Call<T>?,
        crossinline onSuccess: (UserNotification?) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request?.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body() as? UserNotification)
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }
// home Feed
    inline fun <T> asyncFeed(
        request: Call<T>?,
        crossinline onSuccess: (FeedResponse?) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request?.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body() as? FeedResponse)
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }

    //refreshToke

    inline fun <T> asyncRefreshToken(
        request: Call<T>,
        crossinline onSuccess: (Register) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    (response.body() as? Register)?.data?.let {
                        onSuccess(response.body() as Register)
                    }
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }
    //logout

    inline fun <T> asyncLogout(
        request: Call<T>,
        crossinline onSuccess: (LogOut) -> Unit,
        crossinline onError: (String) -> Unit
    ) {

        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    (response.body() as? LogOut)?.data?.let {
                        onSuccess(response.body() as LogOut)
                    }
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }
}
