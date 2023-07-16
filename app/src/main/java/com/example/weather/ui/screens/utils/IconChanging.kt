package com.example.weather.ui.screens.utils

import com.example.weather.R

fun getWeatherIcon(icon:String): Int = when(icon){
    "01d" -> {
        R.drawable.d01
    }
    "02d" -> {
        R.drawable.d02
    }
    "03d" -> {
        R.drawable.d03
    }
    "04d" -> {
        R.drawable.d04
    }
    "09d" -> {
        R.drawable.d09
    }
    "10d" -> {
        R.drawable.d10
    }
    "11d" -> {
        R.drawable.d11
    }
    "13d" -> {
        R.drawable.d13
    }
    "50d" -> {
        R.drawable.d50
    }
    "01n" -> {
        R.drawable.n01
    }
    "02n" -> {
        R.drawable.n02
    }
    "03n" -> {
        R.drawable.n03
    }
    "04n" -> {
        R.drawable.n04
    }
    "09n" -> {
        R.drawable.n09
    }
    "10n" -> {
        R.drawable.n10
    }
    "11n" -> {
        R.drawable.n11
    }
    "13n" -> {
        R.drawable.n13
    }
    "50n" -> {
        R.drawable.n50
    }
    else -> {
        100
    }
}