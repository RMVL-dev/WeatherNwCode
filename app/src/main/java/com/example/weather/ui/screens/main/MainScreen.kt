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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.Forecast
import com.example.weather.data.Weather
import com.example.weather.ui.screens.forecast.ForecastState
import com.example.weather.ui.screens.forecast.ForecastViewModel
import com.example.weather.ui.screens.main.WeatherState.Success
import com.example.weather.ui.screens.splash.SplashScreen
import com.example.weather.ui.screens.utils.getWeatherIcon
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    forecastViewModel: ForecastViewModel,
    navigateToDetails: () -> Unit,
    navigateToForecast: () -> Unit,
    @DrawableRes backgroundImage: Int
){

    if (
        viewModel.weatherState is WeatherState.Success &&
        forecastViewModel.forecastUiState is ForecastState.Success
    ){
        CurrentWeather(
            backgroundImage = backgroundImage,
            weather = (viewModel.weatherState as Success).weather,
            forecast = (forecastViewModel.forecastUiState as ForecastState.Success).forecast,
            navigateToDetails = {navigateToDetails()},
            navigateToForecast = {navigateToForecast()}
        )
    }else if (
        viewModel.weatherState is WeatherState.Loading &&
        forecastViewModel.forecastUiState is ForecastState.Loading
    ){
        SplashScreen()
    }

    //when(viewModel.weatherState){
    //    is Success -> {
    //        CurrentWeather(
    //            backgroundImage = backgroundImage,
    //            weather = (viewModel.weatherState as Success).weather,
    //            navigateToDetails = {navigateToDetails()},
    //            navigateToForecast = {navigateToForecast()}
    //        )
    //    }
    //    is WeatherState.Error -> {
    //    }
    //    is WeatherState.Loading -> {
    //        SplashScreen()
    //    }
    //}
}
@Composable
fun CurrentWeather(
    modifier: Modifier = Modifier,
    @DrawableRes backgroundImage:Int,
    weather: Weather,
    forecast:Forecast,
    navigateToDetails:()->Unit,
    navigateToForecast: ()->Unit
){
    @DrawableRes val weatherIcon = getWeatherIcon(weather.weather[0].icon)
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
                .fillMaxSize(),
                //.padding(start = 16.dp)
            horizontalAlignment = Alignment.CenterHorizontally
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
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { navigateToDetails() },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = modifier.height(20.dp))
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
                            .size(40.dp)
                            .padding(end = 3.dp)
                    )
                    Text(
                        text = weather.weather[0].description,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 20.sp
                    )
                }
                //Spacer(modifier = modifier.weight(4f))
                ForecastList(
                    forecast = forecast
                )

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

        }
    }
}


