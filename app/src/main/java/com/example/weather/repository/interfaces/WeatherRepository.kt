package com.example.weather.repository.interfaces

import com.example.weather.data.Forecast
import com.example.weather.data.Weather

interface WeatherRepository {
    suspend fun getWeather():Weather
     suspend fun getForecast():Forecast
}