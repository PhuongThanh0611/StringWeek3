package com.example.week3.stringweek3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.model.FeedResponse
import com.example.week3.stringweek3.model.Result
import com.example.week3.stringweek3.repository.Repository

class NavHomeViewModel (private val repository : Repository) : ViewModel(){
    private  val intersets = MutableLiveData<Result<FeedResponse>>()
    val resultHomeFeed = Transformations.switchMap(intersets){
        it.data
    }!!

    val networkHomeFeed = Transformations.switchMap(intersets){
        it.networkState
    }!!
    fun getHomeFeed(page : String , current_page :String ,token :String ){
        intersets.value=repository.getFeed(page, current_page, token)
    }
    class HomeFeedViewModelFactory(
        private val repository: Repository

    ): ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) = NavHomeViewModel(repository) as T
    }

}