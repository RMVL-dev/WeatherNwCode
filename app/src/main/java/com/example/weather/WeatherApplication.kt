package com.example.weather

import android.app.Application
import com.example.weather.repository.classes.WeatherAppContainer
import com.example.weather.repository.interfaces.AppContainer


class WeatherApplication:Application() {

    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = WeatherAppContainer()
    }

}