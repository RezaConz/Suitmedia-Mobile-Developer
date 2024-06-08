package com.suitmedia.testportal.data

import androidx.lifecycle.LiveData

sealed class Result<out R> private constructor() {
    data class Success<out T>(val data: LiveData<@UnsafeVariance T>) : Result<T>()
    data class Error(val error:String) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}