package com.example.weather.ui.screens.forecast

import com.example.weather.data.Forecast

sealed interface ForecastState {
    data class Success(val forecast: Forecast):ForecastState

    object Loading:ForecastState

    object Error:ForecastState
}