package com.example.week3.stringweek3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.model.Result
import com.example.week3.stringweek3.model.UserNotification
import com.example.week3.stringweek3.repository.Repository

class UserNotificationViewModel(private var repository: Repository): ViewModel() {
    private val userNotification = MutableLiveData<Result<UserNotification>>()
    val resultUserFollow = Transformations.switchMap(userNotification){
        it.data
    }!!

     val networkUserFollow = Transformations.switchMap(userNotification){
        it.networkState
    }!!
    fun putUserNotification(likes: Boolean,comments:Boolean,new_followes: Boolean,post_saves:Boolean ,string :Boolean, token:String ){
        userNotification.value=repository.putUserNotification(likes,comments,new_followes,post_saves,string,token)
    }

    class UserNotificationViewModelFactory(
        private val repository: Repository

    ): ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = UserNotificationViewModel(repository) as T
    }
}