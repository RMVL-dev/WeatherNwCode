package com.example.weather.ui.screens.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.data.Weather


@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
){
    when(viewModel.weatherState){
        is WeatherState.Success -> {
            CurrentWeather(
                backgroundImage = R.drawable.night,
                weather = (viewModel.weatherState as WeatherState.Success).weather
            )
        }
        is WeatherState.Error -> {

        }
        is WeatherState.Loading -> {

        }
    }
}
@Composable
fun CurrentWeather(
    modifier: Modifier = Modifier,
    @DrawableRes backgroundImage:Int,
    weather: Weather
){
    Box(
        modifier = modifier
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = "",
            modifier = modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 110.dp),
            //verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${weather.currentWeather.feelsLike.toInt()}°",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black
            )
            Text(
                text = weather.weather[0].description,
                color = Color.Black
                //style = MaterialTheme.typography.headlineSmall
            )
            Column(modifier = modifier) {
                Row(
                    modifier = modifier.padding(bottom = 16.dp)
                ) {
                    Text(
                        text = weather.name,
                        color = Color.Black
                    )
                    Image(
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = "${weather.currentWeather.tempMax.toInt()}° / ${weather.currentWeather.tempMin.toInt()}°",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
                Text(
                    text = "Ощущается как ${weather.currentWeather.feelsLike.toInt()}°",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
                Row(
                    //modifier = modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.humidity),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                    Text(
                        text = "${weather.currentWeather.humidity}%",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                }
                Row(
                    //modifier = modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.pressure),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                    Text(
                        text = "${(weather.currentWeather.pressure*0.75).toInt()} мм рт. ст.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                }
            }
        }

    }
}
