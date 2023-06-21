package com.example.weather.ui.screens.main

import com.example.weather.data.Weather

sealed interface WeatherState{
    data class Success(val weather: Weather):WeatherState
    object Loading:WeatherState
    object Error:WeatherState
}