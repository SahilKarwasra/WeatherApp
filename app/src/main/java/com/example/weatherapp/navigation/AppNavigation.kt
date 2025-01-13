package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.weatherapp.data.models.WeatherModel
import com.example.weatherapp.screens.WeatherListScreen
import com.example.weatherapp.screens.WeatherScreen
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.WeatherScreenObj
    ) {
        composable<DestinationScreen.WeatherScreenObj> {
            WeatherScreen(navigate = {
                navController.navigate(it)
            })
        }
        composable<DestinationScreen.WeatherListScreenObj> {
            val weatherDataJson = it.toRoute<DestinationScreen.WeatherListScreenObj>().weatherData
            val weatherData = Json.decodeFromString<WeatherModel>(weatherDataJson)
            WeatherListScreen(weatherData)
        }
    }
}