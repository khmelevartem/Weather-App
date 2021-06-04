package com.tubetoast.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tubetoast.weather.model.data.RepositoryImpl
import com.tubetoast.weather.model.domain.interactor.Interactor
import com.tubetoast.weather.model.domain.interactor.InteractorImpl
import com.tubetoast.weather.model.entities.City
import com.tubetoast.weather.model.entities.Weather
import com.tubetoast.weather.viewmodel.entities.AppState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MainViewModel : ViewModel(), Presenter {

    private val appState : MutableLiveData<AppState> = MutableLiveData()
    private val russianCities : MutableLiveData<List<City>> = MutableLiveData()
    private val worldCities : MutableLiveData<List<City>> = MutableLiveData()
    private val weather : MutableLiveData<Weather> = MutableLiveData()
    private val interactor : Interactor = InteractorImpl(RepositoryImpl())
    override var disposable : Disposable? = null

    override fun getAppState() : LiveData<AppState> = appState

    fun getCities(isRussian : Boolean) : LiveData<List<City>> {
        if (isRussian && russianCities.value == null ) loadCities(isRussian)
        if (!isRussian && worldCities.value == null ) loadCities(isRussian)
        return if (isRussian) russianCities else worldCities
    }

    private fun loadCities(isRussian: Boolean = true) {
        disposable = interactor
            .getCities(isRussian)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {
                if (isRussian) russianCities.value = it
                else worldCities.value = it
            },
            {
                appState.value = AppState.Error(it)
            },
            {
                appState.value = AppState.Success
            },
            {
                appState.value = AppState.Loading
            }
        )
    }

    fun getWeather(city : City) : LiveData<Weather> {
        return weather.also { loadWeather(city) }
    }

    private fun loadWeather(city: City) {
        disposable = interactor
            .getWeather(city)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {
                weather.value = it
            },
            {
                appState.value = AppState.Error(it)
            },
            {
                appState.value = AppState.Success
            },
            {
                appState.value = AppState.Loading
            }
        )
    }

    override fun onStart() {

    }

    override fun onStop() {
        disposable?.dispose()
    }

}