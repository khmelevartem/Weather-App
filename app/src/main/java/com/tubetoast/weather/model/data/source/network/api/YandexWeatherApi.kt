package com.tubetoast.weather.model.data.source.network.api

import com.tubetoast.weather.model.data.source.network.api.models.WeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexWeatherApi {

    @GET("v2/informers")
    fun getWeatherDetails(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String = "ru_RU"
    ) : Call<WeatherDTO>

}