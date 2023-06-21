package com.example.weather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.providers.AppViewModelsProvider
import com.example.weather.ui.screens.main.MainViewModel
import com.example.weather.ui.screens.main.WeatherState
import com.example.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestScreen()
                }
            }
        }
    }
}

@Composable
fun TestScreen(
    viewModel:MainViewModel = viewModel(
        factory = AppViewModelsProvider.Factory
    )
){
    when(viewModel.weatherState){
        is WeatherState.Success -> {
            Log.d("ServiceApi", (viewModel.weatherState as WeatherState.Success).weather.name)
        }
        is WeatherState.Loading -> {
            Log.d("ServiceApi", "Loading")
        }
        is WeatherState.Error -> {
            Log.d("ServiceApi", "error")
        }
    }

}
