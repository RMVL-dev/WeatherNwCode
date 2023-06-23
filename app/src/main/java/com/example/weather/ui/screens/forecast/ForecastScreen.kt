package com.example.weather.ui.screens.forecast

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weather.data.Forecast
import com.example.weather.ui.screens.loading.LoadingScreen
import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.ui.layout.ContentScale
import com.example.weather.data.Weather


@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    forecastViewModel: ForecastViewModel,
    @DrawableRes background:Int
){
    when(forecastViewModel.forecastUiState){
        is ForecastState.Success -> {
            Forecast(
                forecast = (forecastViewModel.forecastUiState as ForecastState.Success).forecast,
                background = background
            )
        }
        is ForecastState.Error -> {

        }
        is ForecastState.Loading -> {
            LoadingScreen()
        }
    }
}


@Composable
fun Forecast(
    modifier: Modifier = Modifier,
    forecast: Forecast,
    @DrawableRes background: Int
){
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = background),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ForecastCard(
    modifier: Modifier = Modifier,
    weather:List<Weather>
){
    Card(
        modifier = modifier
    ) {

    }
}