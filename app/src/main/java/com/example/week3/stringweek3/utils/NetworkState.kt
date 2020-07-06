package com.example.week3.stringweek3.utils

data class NetworkState (val status: Status,  val message: String?=null) {
    companion object {
        val LOADED =
            NetworkState(Status.SUCCESS)
        val LOADING =
            NetworkState(Status.LOADING)
        fun error(msg: String?) = NetworkState(
            Status.ERROR,
            msg
        )
    }

}