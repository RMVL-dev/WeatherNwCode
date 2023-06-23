package com.example.weather.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.R
import com.example.weather.ui.screens.main.MainViewModel
import com.example.weather.ui.screens.main.WeatherState

@Composable
fun SplashScreen(
    modifier:Modifier = Modifier,
    //viewModel:MainViewModel,
    //navigateToMainScreen:() -> Unit
){
    //when(viewModel.weatherState){
    //    is WeatherState.Success -> {
    //       navigateToMainScreen()
    //    }
    //    is WeatherState.Error -> {
//
    //    }
    //    is WeatherState.Loading -> {
//
    //    }
    //}
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.splash)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.weather),
            color = colorResource(id = R.color.splash_text),
            style = MaterialTheme.typography.headlineLarge
        )
        LinearProgressIndicator(
            color = colorResource(id = R.color.splash_text)
        )
    }
}
