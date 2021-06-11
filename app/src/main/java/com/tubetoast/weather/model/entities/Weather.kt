package com.tubetoast.weather.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Weather(
    val city: City,
    val fact : @RawValue Fact,
    val forecast1: @RawValue Forecast? = null,
    val forecast2: @RawValue Forecast? = null,

) : Parcelable

data class Fact(
    val temperature: Int,
    val feelsLike: Int,
    val condition : String? = null,
    val icon: String? = null
)

data class Forecast(
    val time: String,
    val temperature: Int,
    val icon: String? = null
)