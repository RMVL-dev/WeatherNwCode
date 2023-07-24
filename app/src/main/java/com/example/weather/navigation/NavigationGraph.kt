package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.providers.AppViewModelsProvider
import com.example.weather.ui.screens.main.CurrentWeatherScreen
import com.example.weather.ui.screens.main.MainViewModel
import com.example.weather.ui.screens.test.Test

enum class NavigationGraph(title:String){
    CurrentWeather(title = "Main"),
    Details(title = "Details"),
    Forecast(title = "Forecast")
}

@Composable
fun WeatherApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    currentWeatherViewModel: MainViewModel = viewModel(
        factory = AppViewModelsProvider.Factory
    ),
){
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.CurrentWeather.name
    ){
        composable(route = NavigationGraph.CurrentWeather.name){
            CurrentWeatherScreen(
                viewModel = currentWeatherViewModel,
                navigateToDetails = {

                },
                navigateToForecast = {
                    navController.navigate(route = NavigationGraph.Forecast.name)
                }
            )
        }
        composable(route = NavigationGraph.Forecast.name){
            Test()
        }
    }

}