package com.example.week3.stringweek3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.model.Result
import com.example.week3.stringweek3.model.UserFollow
import com.example.week3.stringweek3.repository.Repository

class UserFollowViewModel(private val repository : Repository) : ViewModel() {
    private val userfollow = MutableLiveData<Result<UserFollow>>()
    val resultUserFollow = Transformations.switchMap(userfollow){
        it.data
    }!!

    val networkUserFollow = Transformations.switchMap(userfollow){
        it.networkState
    }!!
    fun getUserFollow(page : String , current_page :String ,token :String ){
        userfollow.value=repository.getUserList(page, current_page, token)
    }

    class UserFollowViewModelFactory(
        private val repository: Repository

    ): ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = UserFollowViewModel(repository) as T
    }
}