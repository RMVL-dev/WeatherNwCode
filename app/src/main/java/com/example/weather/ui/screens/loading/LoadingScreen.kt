package com.example.weather.ui.screens.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.weather.R

@Composable
fun LoadingScreen(
    modifier:Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.splash)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.splash_text)
        )
    }
}