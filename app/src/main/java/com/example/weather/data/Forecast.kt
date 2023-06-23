package com.example.weather.data


import com.example.weather.data.utils.City
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerialName("cod")
    val cod:Int,
    @SerialName("list")
    val Forecast:List<Weather>,
    @SerialName("city")
    val cityInfo: City
)
