package com.example.lydiacontactsapp.utils

sealed class Resource<out T>(open val value: T?)
data class Success<T>(override val value: T) : Resource<T>(value)
data class Loading<T>(override val value: T? = null) : Resource<T>(value)
class Error<out T>(val msg : String) : Resource<T>(null)
