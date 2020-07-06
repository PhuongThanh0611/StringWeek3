package com.example.week3.stringweek3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.model.Register
import com.example.week3.stringweek3.model.Result
import com.example.week3.stringweek3.repository.Repository

class RegisterViewModel(private val repository: Repository): ViewModel() {

     private val register = MutableLiveData<Result<Register>>()
     val listregister = Transformations.switchMap(register){
          it.data
     }!!

     val networkState = Transformations.switchMap(register){
          it.networkState
     }!!

     fun getRegister( username:String,name:String,date:String,email:String,pass:String,confirmPass:String){
          register.value= repository.getRegister(username,name,date,email,pass,confirmPass)
     }
     fun postLogin (email : String , password:String){
          register.value=repository.postLogin(email, password)
     }

     class RegisterViewModelFactory(
          private val repository: Repository

     ): ViewModelProvider.NewInstanceFactory(){
          @Suppress("UNCHECKED_CAST")
          override fun <T : ViewModel> create(modelClass: Class<T>) = RegisterViewModel(repository) as T

     }

}