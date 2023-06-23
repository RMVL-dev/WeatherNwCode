package com.example.weather.data.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RiseSet(
    @SerialName("sunrise")
    val sunrise:Int? = null,
    @SerialName("sunset")
    val sunset:Int? = null
)
