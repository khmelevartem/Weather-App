package com.tubetoast.weather.model.data

import com.tubetoast.weather.model.data.source.CitiesSource
import com.tubetoast.weather.model.data.source.WeatherSource
import com.tubetoast.weather.model.data.source.localsource.LocalCitiesSource
import com.tubetoast.weather.model.data.source.network.NetworkWeatherSource
import com.tubetoast.weather.model.domain.repository.Repository
import com.tubetoast.weather.model.entities.City
import com.tubetoast.weather.model.entities.Weather
import io.reactivex.Observable

class RepositoryImpl(
    private val citiesSource : CitiesSource = LocalCitiesSource(),
    private val weatherSource : WeatherSource = NetworkWeatherSource()
) : Repository {

    override fun getCities(isRussian: Boolean): Observable<List<City>> {
        return Observable.just(citiesSource.getCities(isRussian))
    }

    override fun getWeather(city: City) : Observable<Weather> {
        return Observable.create{emitter ->
            weatherSource.getWeatherAsync(city, object : Repository.Callback {

                override fun onResult(w: Weather) {
                    emitter.onNext(w)
                    emitter.onComplete()
                }

                override fun onError(t: Throwable) {
                    emitter.onError(t)
                }

            })
        }
    }

}
