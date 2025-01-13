package com.example.weatherapp.data.remote

import com.example.weatherapp.data.HttpRoutes
import com.example.weatherapp.data.models.WeatherModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType

class WeatherApiImpl(
    private val client: HttpClient,
    private val apiKey: String
) : WeatherApi {


    override suspend fun getWeatherData(location: String): WeatherModel {
        return  client.get(HttpRoutes.locationUrl) {
            contentType(ContentType.Application.Json)
            parameter("key", apiKey)
            parameter("q", location)
        }.body()
    }



}