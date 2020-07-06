package com.example.week3.stringweek3.repository

import androidx.lifecycle.MutableLiveData
import com.example.week3.stringweek3.model.*
import com.example.week3.stringweek3.service.SunflowerApiService
import com.example.week3.stringweek3.utils.NetworkState

class ApiRepository(private val apiService: SunflowerApiService) : Repository {

    //Register
    override fun getRegister(
        username: String,
        name: String,
        date: String,
        email: String,
        pass: String,
        confirmPass: String
    ): Result<Register> {
        val networkState = MutableLiveData<NetworkState>()
        val responseRegister = MutableLiveData<Register>()
        apiService.getRegister(
            username, name, date, email, pass, confirmPass,
            onLoading = {
                networkState.value = (NetworkState.LOADING)
            },
            onSuccess = { reponse ->
                responseRegister.value = reponse
                networkState.postValue(NetworkState.LOADED)
            },
            onError = { message ->
                networkState.postValue(NetworkState.error(message))
            }
        )
        return Result(
            data = responseRegister,
            networkState = networkState
        )
    }
//login
    override fun postLogin(email: String, pass: String): Result<Register> {
        val networkState = MutableLiveData<NetworkState>()
        val responseRegister = MutableLiveData<Register>()
        apiService.postLogin(email, pass,
            onLoading = {
                networkState.value = (NetworkState.LOADING)
            },
            onSuccess = { reponse ->
                responseRegister.value = reponse
                networkState.postValue(NetworkState.LOADED)
            },
            onError = { message ->
                networkState.postValue(NetworkState.error(message))
            })
        return Result(
            data = responseRegister,
            networkState = networkState
        )
    }
//fogotPassword
    override fun postForgotPasswprd(email: String): Result<ForgotPassword> {
        val networkState = MutableLiveData<NetworkState>()
        val responseForgotPass = MutableLiveData<ForgotPassword>()
        apiService.postForgotPassword(email,
            onLoading = {
                networkState.value = NetworkState.LOADING
            },
            onSuccess = { reponse ->
                responseForgotPass.value = reponse
                networkState.postValue(NetworkState.LOADED)

            },
            onError = { message ->
                networkState.postValue(NetworkState.error(message))

            })
        return Result(
            data = responseForgotPass,
            networkState = networkState
        )
    }
// Intersets
    override fun getIntersets(
        page: String,
        current_page: String,
        token: String
    ): Result<Intersets> {
        val networkState = MutableLiveData<NetworkState>()
        val responseIntersets = MutableLiveData<Intersets>()
        apiService.getIntersets(page, current_page, token,
            onLoading = {
                networkState.value = NetworkState.LOADING
            },
            onSuccess = { reponse ->
                responseIntersets.value = reponse
                networkState.postValue(NetworkState.LOADED)

            },
            onError = { message ->
                networkState.postValue(NetworkState.error(message))
            })
        return Result(
            data = responseIntersets,
            networkState = networkState
        )
    }
//Userlist
    override fun getUserList(
        page: String,
        current_page: String,
        token: String
    ): Result<UserFollow> {
        val networkState = MutableLiveData<NetworkState>()
        val responseIntersets = MutableLiveData<UserFollow>()
        apiService.getUserFollow(page, current_page, token,
            onLoading = {
                networkState.value = NetworkState.LOADING
            },
            onSuccess = { reponse ->
                 responseIntersets.value = reponse
                networkState.postValue(NetworkState.LOADED)

            },
            onError = { message ->
                networkState.postValue(NetworkState.error(message))
            })
        return Result(
            data = responseIntersets,
            networkState = networkState
        )
    }
// followUser
    override fun postFollowUsers(
        id: Int,
        token: String
    ): Result<FollowUsers> {
        val networkState = MutableLiveData<NetworkState>()
        val responseIntersets = MutableLiveData<FollowUsers>()
        apiService.postFollowUsers(id, token,
            onLoading = {
                networkState.value = NetworkState.LOADING
            },
            onSuccess = { reponse ->
                responseIntersets.value = reponse
                networkState.postValue(NetworkState.LOADED)

            },
            onError = { message ->
                networkState.postValue(NetworkState.error(message))
            })
        return Result(
            data = responseIntersets,
            networkState = networkState
        )
    }
// Notification
    override fun putUserNotification(
        likes: Boolean,
        comments: Boolean,
        new_followes: Boolean,
        post_saves: Boolean,
        string: Boolean,
        token: String
    ): Result<UserNotification> {
        val networkState = MutableLiveData<NetworkState>()
        val responseUserNotification = MutableLiveData<UserNotification>()
        apiService.putUserNotification(likes, comments, new_followes, post_saves, string, token,
            onLoading = {
                networkState.value = NetworkState.LOADING
            },
            onSuccess = { reponse ->
                responseUserNotification.value = reponse
                networkState.postValue(NetworkState.LOADED)

            },
            onError = { message ->
                networkState.postValue(NetworkState.error(message))
            })
        return Result(
            data = responseUserNotification,
            networkState = networkState
        )

    }
// Feed
    override fun getFeed(page: String, current_page: String, token: String): Result<FeedResponse> {

        val networkState = MutableLiveData<NetworkState>()
        val responseFeed = MutableLiveData<FeedResponse>()
        apiService.getFeed(page,current_page,token,
            onLoading = {
                networkState.value = NetworkState.LOADING
            },
            onSuccess = { reponse ->
                responseFeed.value = reponse
                networkState.postValue(NetworkState.LOADED)

            },
            onError = { message ->
                networkState.postValue(NetworkState.error(message))
            })
        return Result(
            data = responseFeed,
            networkState = networkState
        )
    }

    // refreshToken
    override fun getRefreshToken(token: String): Result<Register> {
        val networkState = MutableLiveData<NetworkState>()
        val responseRefreshToken = MutableLiveData<Register>()
        apiService.getRefershToken(token,
            onLoading = {
                networkState.value = (NetworkState.LOADING)
            },
            onSuccess = { reponse ->
                responseRefreshToken.value = reponse
                networkState.postValue(NetworkState.LOADED)
            },
            onError = { message ->
                networkState.postValue(NetworkState.error(message))
            }
        )
        return Result(
            data = responseRefreshToken,
            networkState = networkState
        )
    }
// logout
    override fun getLogOut(token: String): Result<LogOut> {
    val networkState = MutableLiveData<NetworkState>()
    val responseLogOut = MutableLiveData<LogOut>()
    apiService.getLogOut(token,
        onLoading = {
            networkState.value = (NetworkState.LOADING)
        },
        onSuccess = { reponse ->
            responseLogOut.value = reponse
            networkState.postValue(NetworkState.LOADED)
        },
        onError = { message ->
            networkState.postValue(NetworkState.error(message))
        }
    )
    return Result(
        data = responseLogOut,
        networkState = networkState
    )
    }
}