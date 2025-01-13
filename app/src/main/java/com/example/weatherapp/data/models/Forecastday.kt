package com.example.weatherapp.data.models

import kotlinx.serialization.Serializable


@Serializable
data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Long,
    val day: Day,
    val hour: List<Hour>
)