package com.example.weather.ui.screens.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.data.Weather
import com.example.weather.ui.screens.splash.SplashScreen


@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navigateToDetails: () -> Unit,
    navigateToForecast: () -> Unit,
    @DrawableRes backgroundImage: Int
){
    when(viewModel.weatherState){
        is WeatherState.Success -> {
            CurrentWeather(
                backgroundImage = backgroundImage,
                weather = (viewModel.weatherState as WeatherState.Success).weather,
                navigateToDetails = {navigateToDetails()},
                navigateToForecast = {navigateToForecast()}
            )
        }
        is WeatherState.Error -> {

        }
        is WeatherState.Loading -> {
            SplashScreen()
        }
    }
}
@Composable
fun CurrentWeather(
    modifier: Modifier = Modifier,
    @DrawableRes backgroundImage:Int,
    weather: Weather,
    navigateToDetails:()->Unit,
    navigateToForecast: ()->Unit
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
                .padding(start = 16.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 110.dp)
                    .clickable { navigateToDetails() },
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
                        weather.name?.let {
                            Text(
                                text = it,
                                color = Color.Black
                            )
                        }
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
                            text = "${(weather.currentWeather.pressure * 0.75).toInt()} мм рт. ст.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = modifier.weight(1f))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp, end = 20.dp)
                    ,
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        modifier = modifier,
                        onClick = { navigateToForecast() }
                    ) {
                        Text(text = stringResource(id = R.string.forecast))
                    }
                }
            }
        }
    }
}
