package com.example.weather.network.service

import com.example.weather.data.Forecast
import com.example.weather.data.Weather
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherService {
    @GET
    suspend fun getWeather(@Url url:String): Weather

    @GET
    suspend fun getForecast(@Url url: String):Forecast
}