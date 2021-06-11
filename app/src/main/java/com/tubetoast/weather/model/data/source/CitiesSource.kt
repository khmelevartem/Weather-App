package com.tubetoast.weather.model.data.source

import com.tubetoast.weather.model.entities.City

interface CitiesSource {
    fun getCities(isRussian: Boolean) : List<City>
}
