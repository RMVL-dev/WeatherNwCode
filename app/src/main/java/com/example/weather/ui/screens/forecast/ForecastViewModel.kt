package com.example.weather.ui.screens.forecast

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.repository.interfaces.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ForecastViewModel(
    private val repository: WeatherRepository
):ViewModel() {

    var forecastUiState:ForecastState by mutableStateOf(ForecastState.Loading)
        private set

    init {
        getForecast()
    }

    fun getForecast(){
        viewModelScope.launch {
            forecastUiState = try {
                ForecastState.Success(
                    repository.getForecast()
                )
            }catch (e:HttpException){
                ForecastState.Error
            }catch (e:IOException){
                ForecastState.Error
            }
        }
    }
}