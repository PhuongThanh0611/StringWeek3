package com.example.week3.stringweek3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.model.ForgotPassword
import com.example.week3.stringweek3.model.Result
import com.example.week3.stringweek3.repository.Repository

class ForgotPasswordViewModel( private val repository:Repository): ViewModel() {

    private val forgotpassword = MutableLiveData<Result<ForgotPassword>>()
    val listForgot = Transformations.switchMap(forgotpassword){
       it.data
    }!!

    val networkForgot = Transformations.switchMap(forgotpassword){
        it.networkState
    }!!
    fun postRogetPass(email:String){
        forgotpassword.value=repository.postForgotPasswprd(email)
    }

    class ForgotPasswordViewModelFactory(
        private val repository: Repository

    ): ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = ForgotPasswordViewModel(repository) as T
    }

}