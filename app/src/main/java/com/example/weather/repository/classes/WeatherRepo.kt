package com.example.weather.repository.classes

import com.example.weather.data.Forecast
import com.example.weather.data.Weather
import com.example.weather.network.service.WeatherService
import com.example.weather.repository.interfaces.WeatherRepository

class WeatherRepo(
    private val retrofit:WeatherService
):WeatherRepository {
    override suspend fun getWeather(): Weather =
        retrofit.getWeather("weather?lat=59.940740&lon=30.314292&appid=d9e6fe2ca9bd114df14262b014663852&units=metric&lang=ru")

    override suspend fun getForecast(): Forecast =
        retrofit.getForecast("forecast?lat=59.940740&lon=30.314292&appid=d9e6fe2ca9bd114df14262b014663852&units=metric&lang=ru")
}