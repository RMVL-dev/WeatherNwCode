package com.example.weather.ui.screens.utils

import com.example.weather.R
import java.util.Calendar

fun getBackgroundImage(): Int = when(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)){
    in 5 until 10 ->{
        R.drawable.sunrise
    }
    in 10 until 18 -> {
        R.drawable.day
    }
    in 18 until 22 ->{
        R.drawable.sunset
    }
    else -> R.drawable.night
}