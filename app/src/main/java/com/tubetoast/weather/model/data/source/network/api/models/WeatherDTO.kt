package com.tubetoast.weather.model.data.source.network.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("now")
    @Expose
    val now: Float,
    @SerializedName("now_dt")
    @Expose
    val nowDt: String,
    @SerializedName("info")
    @Expose
    val info: Info,
    @SerializedName("fact")
    @Expose
    val fact: Fact,
    @SerializedName("forecast")
    @Expose
    val forecast: Forecast
)

data class Info (
    @SerializedName("lat")
    @Expose
    val lat: Float,
    @SerializedName("lon")
    @Expose
    val lon: Float,
    @SerializedName("url")
    @Expose
    val url: String
)

data class Fact(
    @SerializedName("temp")
    @Expose
    val temp: Float,
    @SerializedName("feels_like")
    @Expose
    val feelsLike: Float,
    @SerializedName("icon")
    @Expose
    val icon: String,
    @SerializedName("condition")
    @Expose
    val condition: String,
    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Float,
    @SerializedName("wind_gust")
    @Expose
    val windGust: Float,
    @SerializedName("wind_dir")
    @Expose
    val windDir: String,
    @SerializedName("pressure_mm")
    @Expose
    val pressureMm: Float,
    @SerializedName("pressure_pa")
    @Expose
    val pressurePa: Float,
    @SerializedName("humidity")
    @Expose
    val humidity: Float,
    @SerializedName("daytime")
    @Expose
    val daytime: String,
    @SerializedName("polar")
    @Expose
    val polar: Boolean,
    @SerializedName("season")
    @Expose
    val season: String,
    @SerializedName("obs_time")
    @Expose
    val obsTime: Float
)

data class Forecast(
    @SerializedName("date")
    @Expose
    val date: String,
    @SerializedName("date_ts")
    @Expose
    val dateTs: Float,
    @SerializedName("week")
    @Expose
    val week: Float,
    @SerializedName("sunrise")
    @Expose
    val sunrise: String,
    @SerializedName("sunset")
    @Expose
    val sunset: String,
    @SerializedName("moon_code")
    @Expose
    val moonCode: Float,
    @SerializedName("moon_text")
    @Expose
    val moonText: String,
    @SerializedName("parts")
    @Expose
    val parts: List<Part>)

data class Part(
    @SerializedName("part_name")
    @Expose
    val partName: String,
    @SerializedName("temp_min")
    @Expose
    val tempMin: Float,
    @SerializedName("temp_max")
    @Expose
    val tempMax: Float,
    @SerializedName("temp_avg")
    @Expose
    val tempAvg: Float,
    @SerializedName("feels_like")
    @Expose
    val feelsLike: Float,
    @SerializedName("icon")
    @Expose
    val icon: String,
    @SerializedName("condition")
    @Expose
    val condition: String,
    @SerializedName("daytime")
    @Expose
    val daytime: String,
    @SerializedName("polar")
    @Expose
    val polar: Boolean,
    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Float,
    @SerializedName("wind_gust")
    @Expose
    val windGust: Float,
    @SerializedName("wind_dir")
    @Expose
    val windDir: String,
    @SerializedName("pressure_mm")
    @Expose
    val pressureMm: Float,
    @SerializedName("pressure_pa")
    @Expose
    val pressurePa: Float,
    @SerializedName("humidity")
    @Expose
    val humidity: Float,
    @SerializedName("prec_mm")
    @Expose
    val precMm: Float,
    @SerializedName("prec_period")
    @Expose
    val precPeriod: Float,
    @SerializedName("prec_prob")
    @Expose
    val precProb: Float
)