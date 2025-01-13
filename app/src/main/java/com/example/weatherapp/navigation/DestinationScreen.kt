package com.example.weatherapp.navigation

import com.example.weatherapp.data.models.WeatherModel
import kotlinx.serialization.Serializable

sealed class DestinationScreen{

    @Serializable
    data object WeatherScreenObj : DestinationScreen()

    @Serializable
    data class WeatherListScreenObj(
        val weatherData: String
    ): DestinationScreen()


}