package com.example.week3.stringweek3.model

import androidx.lifecycle.LiveData
import com.example.week3.stringweek3.utils.NetworkState

data class Result <T>(
    val data :LiveData<T>,
    val networkState :LiveData <NetworkState>
)