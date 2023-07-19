package com.example.weather.data.utils

import androidx.annotation.DrawableRes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("id")
    val id: Int,
    @SerialName("main")
    val main: String,
    @SerialName("description")
    val description:String,
    @SerialName("icon")
    val iconRef: String,
    @DrawableRes
    var iconInner:Int? = null
)
