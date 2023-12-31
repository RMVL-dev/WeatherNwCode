package com.example.weather.ui.screens.details

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.data.Weather
import com.example.weather.ui.screens.loading.LoadingScreen
import com.example.weather.ui.screens.main.MainViewModel
import com.example.weather.ui.screens.main.WeatherState
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel:MainViewModel,
    navigateToCurrent: () -> Unit,
    @DrawableRes backgroundImage:Int,
){
    when(viewModel.weatherState){
        is WeatherState.Success -> {
            Details(
                weather = (viewModel.weatherState as WeatherState.Success).weather,
                backgroundImage = backgroundImage,
                navigateToCurrent = {navigateToCurrent()}
            )
        }
        is WeatherState.Error -> {

        }
        is WeatherState.Loading -> {
            LoadingScreen()
        }
    }
}
@Composable
fun Details(
    modifier: Modifier = Modifier,
    @DrawableRes backgroundImage:Int,
    navigateToCurrent:()->Unit,
    weather: Weather
){
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
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
            Row(
                modifier = modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable { navigateToCurrent() },
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = modifier
                        .padding(end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = "",
                        modifier = modifier.size(64.dp)
                    )
                    Text(
                        text = SimpleDateFormat("HH:mm")
                            .format(
                                Date((weather.sun?.sunrise?.times(1000)) ?: 1)
                            )
                    )
                }
                Box(modifier = modifier
                    .height(80.dp)
                    .width(2.dp)
                    .background(Color.Black)) {

                }
                Column(
                    modifier = modifier.padding(start = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.moon),
                        contentDescription = "",
                        modifier = modifier.size(64.dp)
                    )
                    Text(
                        text = SimpleDateFormat("HH:mm")
                            .format(
                                Date((weather.sun?.sunset?.times(1000)) ?: 1)
                            )
                    )
                }
            }
        }
    }
}