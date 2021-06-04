package com.tubetoast.weather.model.domain.repository

import com.tubetoast.weather.model.entities.City
import com.tubetoast.weather.model.entities.Weather
import io.reactivex.Observable

interface Repository {
    fun getCities(isRussian : Boolean) : Observable<List<City>>
    fun getWeather(city : City) : Observable<Weather>

    interface Callback {
        fun onResult(w : Weather)
        fun onError(t : Throwable)
    }
}