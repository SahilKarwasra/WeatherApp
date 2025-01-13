package com.example.weatherapp.data.remote

import com.example.weatherapp.data.models.WeatherModel

interface WeatherApi {
    suspend fun getWeatherData(location: String): WeatherModel
}
