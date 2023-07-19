package com.example.weather.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.repository.interfaces.WeatherRepository
import com.example.weather.ui.screens.forecast.ForecastState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(
    private val repository: WeatherRepository
):ViewModel() {

    init {
        getCurrentWeather()
        getForecast()
    }

    var weatherState:WeatherState by mutableStateOf(WeatherState.Loading)
        private set
    var forecastUiState:ForecastState by mutableStateOf(ForecastState.Loading)
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
    private fun getForecast(){
        viewModelScope.launch {
            forecastUiState = try {
                ForecastState.Success(repository.getForecast())
            }catch (e:IOException){
                ForecastState.Error
            }catch (e:HttpException){
                ForecastState.Error
            }
        }
    }

}