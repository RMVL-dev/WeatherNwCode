package com.example.weather.data.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RiseSet(
    @SerialName("sunrise")
    val sunrise:Int,
    @SerialName("sunset")
    val sunset:Int
)
