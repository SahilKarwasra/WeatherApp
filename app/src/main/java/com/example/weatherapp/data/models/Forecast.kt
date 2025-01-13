package com.example.weatherapp.data.models

import kotlinx.serialization.Serializable


@Serializable
data class Forecast(
    val forecastday: List<Forecastday>
)