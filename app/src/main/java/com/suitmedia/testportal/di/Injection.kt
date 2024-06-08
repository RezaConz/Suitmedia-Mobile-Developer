package com.suitmedia.testportal.di

import com.suitmedia.testportal.data.remote.retrofit.ApiConfig
import com.suitmedia.testportal.repository.UserRepository

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}