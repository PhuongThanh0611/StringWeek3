package com.example.week3.stringweek3

import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.repository.ApiRepository
import com.example.week3.stringweek3.repository.Repository
import com.example.week3.stringweek3.service.RepositoryAPI
import com.example.week3.stringweek3.service.SunflowerApiService
import com.example.week3.stringweek3.service.ApiService
import com.example.week3.stringweek3.viewmodel.*

object Injection {

    //register
    private fun provideRegisterRepository(): Repository{
        return ApiRepository(provideSunFlowerApiService())
    }
    private fun provideSunFlowerApiService(): SunflowerApiService{
        return SunflowerApiService(RepositoryAPI.createService(ApiService::class.java))
    }

    fun provideRegisterViewModelFactory() : ViewModelProvider.Factory{
        return RegisterViewModel.RegisterViewModelFactory(provideRegisterRepository())
    }
    //forgotPassword
    fun provideForgotPasswordViewModelFectory():ViewModelProvider.Factory{
        return ForgotPasswordViewModel.ForgotPasswordViewModelFactory(provideRegisterRepository())
    }
    //Intersets
    fun provideIntersetsViewModelFactory():ViewModelProvider.Factory{
        return IntersetsViewModel.IntersetsViewModelFactory(provideRegisterRepository())
    }
    //UserFollow
    fun provideUserFollowViewModelFactory():ViewModelProvider.Factory{
        return UserFollowViewModel.UserFollowViewModelFactory(provideRegisterRepository())
    }
    //FollowUsers
    fun provideFollowUserViewModelFactory():ViewModelProvider.Factory{
        return FollowUsersViewModel.FollowUsersViewModelFactory(provideRegisterRepository())
    }

    // userNotification
    fun provideUserNotificationViewModelFactory():ViewModelProvider.Factory{
        return UserNotificationViewModel.UserNotificationViewModelFactory(provideRegisterRepository())
    }
    // homeFeed
    fun provideHomeFeedViewModelFactory():ViewModelProvider.Factory{
        return NavHomeViewModel.HomeFeedViewModelFactory(provideRegisterRepository())
    }
    //refreshToken
    fun provideRefreshTokenViewModelFactory():ViewModelProvider.Factory{
        return RegisterViewModel.RegisterViewModelFactory(provideRegisterRepository())
    }
    //logout
    fun provideLogOutViewModelFactory():ViewModelProvider.Factory{
        return LogOutViewModel.LogOutViewModelFactory(provideRegisterRepository())
    }

}