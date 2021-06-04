package com.tubetoast.weather.model.data.source

import com.tubetoast.weather.model.domain.repository.Repository
import com.tubetoast.weather.model.entities.City

interface WeatherSource {
    fun getWeatherAsync(city: City, callback: Repository.Callback)
}