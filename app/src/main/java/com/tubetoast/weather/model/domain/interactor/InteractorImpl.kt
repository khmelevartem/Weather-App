package com.tubetoast.weather.model.domain.interactor

import com.tubetoast.weather.model.domain.repository.Repository
import com.tubetoast.weather.model.entities.City
import com.tubetoast.weather.model.entities.Weather
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class InteractorImpl (
    private val repository: Repository
): Interactor {

    override fun getCities(isRussian: Boolean): Observable<List<City>> {
        return repository
            .getCities(isRussian)
            .subscribeOn(Schedulers.io())
    }

    override fun getWeather(city: City): Observable<Weather> {
        return repository
            .getWeather(city)
            .subscribeOn(Schedulers.io())
    }

}