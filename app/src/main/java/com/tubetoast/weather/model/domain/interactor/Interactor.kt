package com.tubetoast.weather.model.domain.interactor

import com.tubetoast.weather.model.entities.City
import com.tubetoast.weather.model.entities.Weather
import io.reactivex.Observable

interface Interactor {
    fun getCities(isRussian : Boolean = true) : Observable<List<City>>
    fun getWeather(city: City) : Observable<Weather>
}