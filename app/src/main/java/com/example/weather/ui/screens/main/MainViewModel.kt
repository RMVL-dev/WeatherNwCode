package com.example.weather.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.repository.interfaces.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(
    private val repository: WeatherRepository
):ViewModel() {

    init {
        getCurrentWeather()
    }

    var weatherState:WeatherState by mutableStateOf(WeatherState.Loading)
        private set

    private fun getCurrentWeather(){
        viewModelScope.launch{
            weatherState = try {
                WeatherState.Success(repository.getWeather())
            }catch (e:HttpException){
                WeatherState.Error
            }catch (e:IOException){
                WeatherState.Error
            }

        }
    }


}