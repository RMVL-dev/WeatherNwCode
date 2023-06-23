package com.example.weather.data.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("speed")
    val speed:Float,
    @SerialName("deg")
    val deg:Int
)
