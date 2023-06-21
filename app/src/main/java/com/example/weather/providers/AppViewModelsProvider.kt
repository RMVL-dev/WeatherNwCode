package com.example.weather.providers

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weather.WeatherApplication
import com.example.weather.ui.screens.main.MainViewModel

object AppViewModelsProvider {
    val Factory = viewModelFactory {
        initializer {
            MainViewModel(weatherApplication().appContainer.weatherRepository)
        }
    }
}

fun CreationExtras.weatherApplication(): WeatherApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WeatherApplication)