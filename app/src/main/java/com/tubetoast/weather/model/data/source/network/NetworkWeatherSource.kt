package com.tubetoast.weather.model.data.source.network

import com.tubetoast.weather.model.data.source.WeatherSource
import com.tubetoast.weather.model.data.source.network.api.YandexWeatherService
import com.tubetoast.weather.model.data.source.network.api.models.WeatherDTO
import com.tubetoast.weather.model.domain.repository.Repository
import com.tubetoast.weather.model.entities.City
import com.tubetoast.weather.model.entities.Fact
import com.tubetoast.weather.model.entities.Forecast
import com.tubetoast.weather.model.entities.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkWeatherSource : WeatherSource {

    private val api = YandexWeatherService.api

    override fun getWeatherAsync(city: City, callback: Repository.Callback) {
        api.getWeatherDetails(city.lat, city.lon).enqueue(object : Callback<WeatherDTO>{
            override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {

                if (!response.isSuccessful) {
                    callback.onError(RuntimeException("Corrupted request"))
                    return
                }

                val body = response.body()
                if (body == null){
                    callback.onError(RuntimeException("Corrupted data"))
                } else {
                    val factWeather = body.let {
                        Weather(
                            city,
                            Fact(it.fact.temp.toInt(),
                                it.fact.feelsLike.toInt(),
                                it.fact.condition,
                                it.fact.icon),
                            Forecast(it.forecast.parts[0].partName,
                                it.forecast.parts[0].tempAvg.toInt(),
                                it.forecast.parts[0].icon
                                ),
                            Forecast(it.forecast.parts[1].partName,
                                it.forecast.parts[1].tempAvg.toInt(),
                                it.forecast.parts[1].icon
                            )
                        )
                    }

                    callback.onResult(factWeather)
                }
            }

            override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                callback.onError(RuntimeException("Network error : " + t.localizedMessage))
                t.printStackTrace()
            }

        })
    }

}
