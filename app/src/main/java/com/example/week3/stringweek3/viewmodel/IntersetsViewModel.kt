package com.example.week3.stringweek3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.model.Intersets
import com.example.week3.stringweek3.model.Result
import com.example.week3.stringweek3.repository.Repository

class IntersetsViewModel(private val repository :Repository) : ViewModel(){

    private val intersets = MutableLiveData<Result<Intersets>>()
    val resultIntersets = Transformations.switchMap(intersets){
        it.data
    }!!

    val networkIntersets = Transformations.switchMap(intersets){
        it.networkState
    }!!
    fun getIntersets(page : String , current_page :String ,token :String ){
        intersets.value=repository.getIntersets(page, current_page, token)
    }
    class IntersetsViewModelFactory(
        private val repository: Repository

    ): ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = IntersetsViewModel(repository) as T
    }
}