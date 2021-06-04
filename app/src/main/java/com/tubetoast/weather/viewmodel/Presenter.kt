package com.tubetoast.weather.viewmodel

import androidx.lifecycle.LiveData
import com.tubetoast.weather.viewmodel.entities.AppState
import io.reactivex.disposables.Disposable

interface Presenter {

     var disposable : Disposable?

    fun onStart()
    fun onStop()
    fun getAppState() : LiveData<AppState>

}