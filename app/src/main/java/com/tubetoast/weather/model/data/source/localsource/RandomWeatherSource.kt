package com.tubetoast.weather.model.data.source.localsource

import com.tubetoast.weather.model.data.source.WeatherSource
import com.tubetoast.weather.model.domain.repository.Repository
import com.tubetoast.weather.model.entities.City
import com.tubetoast.weather.model.entities.Fact
import com.tubetoast.weather.model.entities.Weather
import java.util.*

class RandomWeatherSource : WeatherSource {

    override fun getWeatherAsync(city: City, callback: Repository.Callback) {
        Thread.sleep(1000)
        val rnd = Random()
        callback.onResult(
            Weather(
                city,
                Fact(rnd.nextInt(20),
                    rnd.nextInt(20),
                    condition = "Облачно")
            )
        )
    }

}