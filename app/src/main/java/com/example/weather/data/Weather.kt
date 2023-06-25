package com.example.weather.data

import com.example.weather.data.utils.Clouds
import com.example.weather.data.utils.CurrentWeather
import com.example.weather.data.utils.Main
import com.example.weather.data.utils.RiseSet
import com.example.weather.data.utils.Wind
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("weather")
    val weather:List<CurrentWeather>,
    @SerialName("main")
    val currentWeather: Main,
    @SerialName("wind")
    val wind: Wind,
    @SerialName("clouds")
    val clouds: Clouds,
    @SerialName("dt")
    val dt:Long,
    @SerialName("sys")
    val sun:RiseSet? = null,
    @SerialName("name")
    val name:String? = null,
    @SerialName("cod")
    val responseCode:Int? = null
)
