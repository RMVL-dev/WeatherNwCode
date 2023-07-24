package com.example.weather.ui.screens.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.Forecast
import com.example.weather.data.Weather
import com.example.weather.ui.screens.forecast.ForecastState
import com.example.weather.ui.screens.main.WeatherState.Success
import com.example.weather.ui.screens.splash.SplashScreen
import com.example.weather.ui.screens.test.TestMainScreen
import com.example.weather.ui.screens.utils.getWeatherIcon
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navigateToDetails: () -> Unit,
    navigateToForecast: () -> Unit,
){

    if (
        viewModel.weatherState is WeatherState.Success &&
        viewModel.forecastUiState is ForecastState.Success
    ){
        TestMainScreen(
            weather = (viewModel.weatherState as Success).weather,
            forecast = (viewModel.forecastUiState as ForecastState.Success).forecast,
            navigateToDetails = {navigateToDetails()},
            navigateToForecast = {navigateToForecast()}
        )
    }else if (
        viewModel.weatherState is WeatherState.Loading &&
        viewModel.forecastUiState is ForecastState.Loading
    ){
        SplashScreen()
    }
}
@Composable
fun TestMainWeather(
    modifier: Modifier = Modifier,
    weather: Weather,
    navigateToForecast: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        weather.name?.let {location ->
            Text(
                text = location,
                modifier = modifier,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
        }
        Divider(color = Color.White, thickness = 1.dp)
        Spacer(modifier = modifier.height(20.dp))
        MainWeather(
            weather = weather,
            navigateToDetails = { /*TODO*/ },
            navigateToForecast = { navigateToForecast() }
        )
    }
}

@Composable
fun MainWeather(
    modifier: Modifier = Modifier,
    weather: Weather,
    navigateToDetails:()->Unit,
    navigateToForecast: ()->Unit
){
    @DrawableRes val weatherIcon = getWeatherIcon(weather.weather[0].iconRef)
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable { navigateToForecast() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = SimpleDateFormat("E, d MMMM, HH:mm")
                .format(
                    Date(weather.dt*1000)
                ),
            color = Color.White
        )
        Text(
            text = "${weather.currentWeather.feelsLike.toInt()}°",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontSize = 80.sp
        )
        Row(
            modifier = modifier
                .wrapContentWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = weatherIcon),
                contentDescription = "",
                modifier = modifier
                    .size(60.dp)
                    .padding(end = 10.dp)
            )
            Text(
                text = weather.weather[0].description,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 40.sp
            )
        }
    }
}


@Composable
fun ForecastList(
    modifier: Modifier = Modifier,
    forecast:Forecast
){
    Column(
        modifier = modifier
            .wrapContentSize()
    ) {
        Box(modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.background))
            .height(350.dp)
            .width(300.dp)
            .padding(10.dp)
        ) {
            LazyColumn(
                modifier = modifier
            ){
                items(forecast.Forecast) { item ->
                    ForecastMainCard(
                        weather = item,
                    )
                }
            }
        }
    }
}

@Composable
fun ForecastMainCard(
    modifier: Modifier = Modifier,
    weather: Weather
){
    Card (
        modifier = modifier
            .padding(3.dp)
            .wrapContentSize()
            .background(color = colorResource(id = R.color.background))
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.background))
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier
            ) {
                Text(
                    text = SimpleDateFormat("dd.MM")
                        .format(Date(weather.dt * 1000)),
                    color = Color.Black
                )
                Text(
                    text = SimpleDateFormat("HH:mm")
                        .format(Date(weather.dt*1000)),
                    color = Color.Black
                )
            }
            weather.weather[0].iconInner?.let { painterResource(id = it) }?.let {
                Image(
                    painter = it,
                    contentDescription = weather.weather[0].description,
                    modifier = modifier.size(50.dp)
                )
            }
            Text(
                text = "${weather.currentWeather.feelsLike.toInt()}°",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )
        }
    }
}


