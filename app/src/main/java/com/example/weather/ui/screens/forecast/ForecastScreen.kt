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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weather.R
import com.example.weather.data.Weather
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    forecastViewModel: ForecastViewModel,
    navigateToCurrent: () -> Unit,
    @DrawableRes background:Int
){
    when(forecastViewModel.forecastUiState){
        is ForecastState.Success -> {
            Forecast(
                forecast = (forecastViewModel.forecastUiState as ForecastState.Success).forecast,
                background = background,
                navigateToCurrent = {navigateToCurrent()}
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
    navigateToCurrent:()->Unit,
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
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = modifier.wrapContentHeight(),
                //verticalArrangement = Arrangement.Bottom
            ) {
                LazyRow(
                    modifier = modifier
                        .wrapContentHeight()
                        .padding(
                            bottom = 100.dp,
                            start = 5.dp,
                            end = 5.dp
                        ),
                    //verticalAlignment = Alignment.Bottom
                ) {
                    items(
                        forecast.Forecast
                    ) { item ->
                        ForecastCard(weather = item)
                    }
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp, end = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    modifier = modifier,
                    onClick = { navigateToCurrent() }
                ) {
                    Text(text = stringResource(id = R.string.current))
                }
            }
        }
    }
}

@Composable
fun ForecastCard(
    modifier: Modifier = Modifier,
    weather:Weather
){
    Card(
        modifier = modifier
            .wrapContentHeight()
            .background(Color(0x00000000))
            .padding(4.dp),

    ) {
        Column(modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://openweathermap.org/img/wn/${weather.weather[0].iconRef}@2x.png")
                    .crossfade(true).build(),
                contentDescription = ""
            )
            Text(
                text = "${weather.currentWeather.feelsLike.toInt()}°",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black
            )
            Text(
                text = "${weather.currentWeather.tempMax.toInt()}° / ${weather.currentWeather.tempMin.toInt()}°",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
            Text(
                text = SimpleDateFormat("HH")
                    .format(
                        Date(weather.dt*1000)
                    ),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
            Text(
                text = SimpleDateFormat("MMMM.dd")
                    .format(
                        Date(weather.dt*1000)
                    ),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
        }
    }
}