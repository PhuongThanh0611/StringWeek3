package com.example.week3.stringweek3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.model.LogOut
import com.example.week3.stringweek3.model.Result
import com.example.week3.stringweek3.repository.Repository

class LogOutViewModel(private val repository : Repository) : ViewModel() {
    private val logOut = MutableLiveData<Result<LogOut>>()
    val resultLogOut = Transformations.switchMap(logOut){
        it.data
    }!!

    val networkIntersets = Transformations.switchMap(logOut){
        it.networkState
    }!!
    fun getIntersets(token :String ){
        logOut.value=repository.getLogOut(token)
    }
    class LogOutViewModelFactory(
        private val repository: Repository

    ): ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = LogOutViewModel(repository) as T
    }
}