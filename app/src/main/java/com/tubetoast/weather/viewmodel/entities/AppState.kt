package com.tubetoast.weather.viewmodel.entities

sealed class AppState {
    object Loading : AppState()
    object Success :  AppState()
    data class Error(val error: Throwable? = null) : AppState()
}