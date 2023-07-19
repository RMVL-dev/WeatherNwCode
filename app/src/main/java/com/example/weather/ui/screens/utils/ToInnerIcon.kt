package com.example.weather.ui.screens.utils

import com.example.weather.data.Forecast

fun Forecast.toChangeIcon(){
    for (i in Forecast){
        i.weather[0].iconInner = getWeatherIcon(
                i.weather[0].iconRef
            )

    }
}