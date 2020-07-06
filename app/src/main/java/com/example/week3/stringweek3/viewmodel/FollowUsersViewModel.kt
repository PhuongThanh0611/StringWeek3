package com.example.week3.stringweek3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.model.FollowUsers
import com.example.week3.stringweek3.model.Result
import com.example.week3.stringweek3.repository.Repository

class FollowUsersViewModel(private var repository :Repository): ViewModel() {

    private val followUsers = MutableLiveData<Result<FollowUsers>>()
    val resultFollowUsers = Transformations.switchMap(followUsers){
        it.data
    }!!

    val networkFollowUsers= Transformations.switchMap(followUsers){
        it.networkState
    }!!
    fun postFollowUsers(id:Int ,token :String ){
        followUsers.value=repository.postFollowUsers(id, token)
    }
    class FollowUsersViewModelFactory(
        private val repository: Repository

    ): ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = FollowUsersViewModel(repository) as T
    }
}