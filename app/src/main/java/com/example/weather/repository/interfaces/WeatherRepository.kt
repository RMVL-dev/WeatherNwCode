package com.example.weather.repository.interfaces

import com.example.weather.data.Weather

interface WeatherRepository {
    suspend fun getWeather():Weather
}