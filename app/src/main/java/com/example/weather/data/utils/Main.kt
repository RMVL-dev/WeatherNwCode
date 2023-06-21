package com.example.weather.data.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("temp")
    val temp:Float,
    @SerialName("feels_like")
    val feelsLike:Float,
    @SerialName("temp_min")
    val tempMin: Float,
    @SerialName("temp_max")
    val tempMax:Float,
    @SerialName("pressure")
    val pressure:Int,
    @SerialName("humidity")
    val humidity:Int
)
