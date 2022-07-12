package com.example.dolananlist.core.data.remote.retrofit

sealed class ApiResponse<out R> private constructor(){
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
    object Loading: ApiResponse<Nothing>()
}
